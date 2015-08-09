package personal.deon.framework.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;


public class HttpNetUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpNetUtil.class);
	
	/**
	 * post方式访问，参数是json字符串，无参数时可以传人null
	 * @param url
	 * @param jsonParams
	 * @return
	 */
	public static String postJson(String url,String json) {
		String result = null;
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost postMethod = new HttpPost(url);
		try {
			if(null != json){
				postMethod.addHeader("content-type", "application/json");  
				postMethod.setEntity(new StringEntity(json,"UTF-8"));
			}
			HttpResponse response = httpClient.execute(postMethod);
			if(response.getStatusLine().getStatusCode()==200)
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			else
				logger.warn("post访问响应错误，status:{}",response.getStatusLine().getStatusCode());
		} catch (UnsupportedEncodingException e) {
			logger.error("post访问传入参数json数据不支持utf-8编码",e);
		} catch (ClientProtocolException e) {
			logger.error("post访问json参数，不支持http协议",e);
		} catch (IOException e) {
			logger.error("post访问json数据传输失败",e);
		}
		logger.info("{}: result={}",url,result);
		return result;
	}
	
	/**
	 * get方式访问数据
	 * @param url
	 * @return
	 */
	public static String getJson(String url){
		String result = null;
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet getMethod = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(getMethod);
			if(response.getStatusLine().getStatusCode() == 200)
				result = EntityUtils.toString(response.getEntity(),"UTF-8");
			else
				logger.warn("get访问响应错误，status:{}",response.getStatusLine().getStatusCode());
		} catch (UnsupportedEncodingException e) {
			logger.error("get访问参数不支持utf-8编码",e);
		} catch (ClientProtocolException e) {
			logger.error("get访问不支持http协议",e);
		} catch (IOException e) {
			logger.error("get访问数据传输失败",e);
		}
		logger.info("{}: result={}",url,result);
		return result;
	}
	/**
	 * 上传图片或语音素材到微信
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static String uploadImgOrVoice(String url, String filePath){
		File file = new File(filePath);
		if(!file.exists())
			return null;
		OutputStream output=null;
		InputStream input = null,response = null;
		try{
			String boundary = "-----------------------------"+System.currentTimeMillis();
			HttpURLConnection conn = connection(url,boundary);
			input = new FileInputStream(file);
			output = conn.getOutputStream();
			writeDate(output,input,boundary,file.getName());
			output.flush();
			output.close();
			input.close();
			
			response = conn.getInputStream();
			String result = readResponse(response);
			response.close();
			return result;
		}catch(Exception e){
			logger.error("上传图片或语音失败",e);
		}finally{
				try {
					if(output != null)output.close();
					if(input != null) input.close();
					if(response != null) response.close();
				} catch (IOException e) {
					logger.error("字节流关闭异常",e);
				}
		}
		return null;
	}
	/**
	 * 上传视频文件到微信
	 * @param url
	 * @param filePath
	 * @param title
	 * @param descr
	 * @return
	 */
	public static String uploadVideo(String url,String filePath,String title,String descr){
		File file = new File(filePath);
		if(!file.exists())
			return null;
		OutputStream output=null;
		InputStream input = null,response = null;
		try{
			String boundary = "-----------------------------"+System.currentTimeMillis();
			HttpURLConnection conn = connection(url,boundary);
			input = new FileInputStream(file);
			output = conn.getOutputStream();
			writeDate(output,input,boundary,file.getName());
			StringBuffer sb = new StringBuffer("Content-Disposition: form-data; name=\"description\";\r\n\r\n");
			Map<String, String> description = Maps.newHashMap();
			description.put("title", title);
			description.put("introduction", descr);
			sb.append(new JSONObject(description).toString())
			.append("\r\n--").append(boundary).append("--\r\n\r\n");
			output.write(sb.toString().getBytes());
			output.flush();
			output.close();
			input.close();
			
			response = conn.getInputStream();
			String result = readResponse(response);
			response.close();
			return result;
		}catch(Exception e){
			logger.error("上传视频失败",e);
		}finally{
			try {
				if(output != null)output.close();
				if(input != null) input.close();
				if(response != null) response.close();
			} catch (IOException e) {
				logger.error("字节流关闭异常",e);
			}
	}
		return null;
	}
	/**
	 * 创建上传文件的链接
	 * @param url
	 * @param boundary
	 * @return
	 * @throws Exception
	 */
	private static HttpURLConnection connection(String url,String boundary) throws Exception{
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(30000);  
		conn.setDoOutput(true);  
		conn.setDoInput(true);  
		conn.setUseCaches(false);  
		conn.setRequestMethod("POST"); 
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Cache-Control", "no-cache");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
		return conn;
	}
	/**
	 * 将上传文件的字节写入上传管道
	 * @param output
	 * @param input
	 * @param boundary
	 * @param fileName
	 * @throws IOException
	 */
	private static void writeDate(OutputStream output,InputStream input,
			String boundary,String fileName) throws IOException{
		StringBuffer buffer = new StringBuffer();
		buffer.append("--").append(boundary).append("\r\n")
		.append("Content-Disposition: form-data; name=\"media\"; filename=\"").append(fileName).append("\"\r\n")
		.append("Content-Type: ").append(MimeType.fileMimeType(fileName)).append("\r\n\r\n");
		
		output.write(buffer.toString().getBytes());
		byte[] data = new byte[1024];
	    int len =0;
		while((len=input.read(data))>-1){
			output.write(data, 0, len);
		}
		output.write(("--" + boundary + "\r\n").getBytes());
	}
	/**
	 * 获取上传的响应结果
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static String readResponse(InputStream input) throws UnsupportedEncodingException, IOException{
		StringBuffer sb = new StringBuffer();
		int len = 0;
		byte[] data = new byte[512]; 
		while((len= input.read(data))>-1)
			sb.append(new String(data,0,len,"utf-8"));
		logger.info("上传文件结果：{}",sb.toString());
		return sb.toString();
	}
	
	/***
	 * 下载文件到指定目录下
	 * @param url：下载的网址
	 * @param saveDir：保存的目录
	 * @return 返回新建的文件名称
	 */
	public static String downloadFile(String url,String saveDir){
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setConnectTimeout(6000);
//			connection.setReadTimeout(300*1000);
			String fileType = connection.getHeaderField("Content-disposition");
			String fileName = UUID.randomUUID().toString()+
					fileType.substring(fileType.lastIndexOf("."),fileType.length()-1);
			logger.info("Content-disposition:{},fileName:{},path:{}",fileType,fileName,saveDir);
			
			FileUtils.copyInputStreamToFile(connection.getInputStream(),new File(saveDir+fileName));
			return fileName;
		} catch (MalformedURLException e) {
			logger.error("下载文件异常1", e);
		} catch (IOException e) {
			logger.error("下载文件异常2", e);
		} catch (Exception e){
			logger.error("下载文件异常3", e);
		}
		return null;	
	}
}
