/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package personal.deon.framework.data;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import personal.deon.framework.core.entity.CoreUser;
import personal.deon.framework.weixin.entity.WeixinMaterial;
import personal.deon.framework.weixin.service.WeixinConfigService;
import personal.deon.framework.weixin.util.HttpNetUtil;
import personal.deon.framework.weixin.util.WeixinMsgUtil;
import cn.gd.thinkjoy.modules.test.data.RandomData;

public class UserData {

	public static CoreUser randomNewUser() {
		CoreUser user = new CoreUser();
		user.setAccount(RandomData.randomName("user"));
		user.setUserName(RandomData.randomName("User"));
		user.setPlainPwd(RandomData.randomName("password"));

		return user;
	}
	protected static String addUnderscores(String name) {
		StringBuilder buf = new StringBuilder( name.replace('.', '_') );
		for (int i=1; i<buf.length()-1; i++) {
			if (
				Character.isLowerCase( buf.charAt(i-1) ) &&
				Character.isUpperCase( buf.charAt(i) ) &&
				Character.isLowerCase( buf.charAt(i+1) )
			) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}
	public static void main(String[] args) {
//		System.out.println(addUnderscores("typeNname"));
//		String s = "sd_sfs";
//		System.out.println(s.substring("sd_".length()));
//		Date date = new Date();
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(format.format(date));
//		
//		calendar.setTime(date);
//		calendar.add(Calendar.SECOND, 1);
//		System.out.println(format.format(calendar.getTime()));
//		System.out.println(calendar.getTime().after(date));
//		Random random = new Random();
//		StringBuffer sb = new StringBuffer();
//		sb.append(random.nextInt(9)+1).append(random.nextInt(9)+1)
//		.append(random.nextInt(9)+1).append(random.nextInt(9)+1)
//		.append(random.nextInt(9)+1).append(random.nextInt(9)+1);
//		System.out.println(sb);
		
		
		
	
//		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
//		+ "vs6b8n4DFm39Am7Y64WrdIj4xDGeOzwju0JT_Q4AYRUPQ0c-lMotlVDCO2C4hnL9GTreAD2DlyI0bx3jFhBIw0zNZ9zchCthU2I1StBMzi8";
//		String re =null;
//		String file = "/Users/jlusoft/Desktop/test.mp3";
		//url+"&type=voice", &type=video","&type=image"
		
//		DecimalFormat formatNum=new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//		
//		for(int i=1;i<51 ;i++){
//			System.out.println("5000*1.1^"+i+"+5000 = "+formatNum.format(5000*Math.pow(1.1,i)+5000));
//			
//		}
//		System.out.println(WeixinMaterial.Type.THUMB.equals("thumb"));
//		HttpNetUtil.downloadFile("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+
//				"weGYvPAWsXjiIzA0iS9muShGlzTCaWKM1gwmEue4G_ANh4VHH6Wj5KPADir9F_LMK-piERQxfcLwDzgTl4KaES0TuWwUNY8HEyK1VBVzS9w"+
//				"&media_id="+
//				"A_bZhog0QriDlcrFFaiE71zK9duH1DXk34HGQvWROi2oJbkmIh3gspXqw7OIqMBb", "/Users/jlusoft/Documents/");
		menu();
	}
	static void menu(){
		Map<String, Object> menu = Maps.newHashMap();
		Map<String, Object> item = Maps.newHashMap();
		item.put("type","view");
		item.put("name","主页");
		item.put("url","http://www.fuliao168.com/fuliao/mobile");
		List<Object> sub = Lists.newArrayList();
		sub.add(item);
		item = Maps.newHashMap();
		item.put("type","view");
		item.put("name","产品");
		item.put("url","http://www.fuliao168.com/fuliao/mobile/product");
		sub.add(item);
		item = Maps.newHashMap();
		item.put("type","view");
		item.put("name","个人中心");
		item.put("url","http://www.fuliao168.com/fuliao/mobile/usercenter");
		sub.add(item);
		menu.put("button", sub);
		
		String button = new JSONObject(menu).toString();
		System.out.println(button);
		
		String sf=HttpNetUtil.getJson("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+
				"aBxQlUHuEwjQnFdz0MSlMcHy7aeNpW6D2L0GDpA1w1ubmvW0maWXAnq3PmaDYVSTEiwS32nyaC0R2Kes-sQnjwSyZdfb_zBC-B_b5eGgTJY");
		System.out.println(sf);
		sf = HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+
				"aBxQlUHuEwjQnFdz0MSlMcHy7aeNpW6D2L0GDpA1w1ubmvW0maWXAnq3PmaDYVSTEiwS32nyaC0R2Kes-sQnjwSyZdfb_zBC-B_b5eGgTJY", button);
		System.out.println(sf);
	}
}
