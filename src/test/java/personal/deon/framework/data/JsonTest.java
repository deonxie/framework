package personal.deon.framework.data;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

public class JsonTest {
	public static  void  json(String result){
		Map<String, String> params = Maps.newHashMap();
		try {
			JSONObject json = new JSONObject(result);
			if(!json.has("errcode")){
				String[] fields = JSONObject.getNames(json);
				if(fields!=null){
					for(String key :fields)
						params.put(key, json.get(key).toString());
				}
			}else
				System.out.println("获取用户openid失败，{}"+json.getString("errmsg"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(params.size());
	}
	public static void main(String[] args) {
		json("{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200, \"refresh_token\""
				+ ":\"REFRESH_TOKEN\",\"openid\":\"OPENID\",\"scope\":\"SCOPE\",\"unionid\":"
				+ " \"o6_bmasdasdsad6_2sgVt7hMZOPfL\"}");
		json("{\"errcode\":40029,\"errmsg\":\"invalid code\"}");
	}
}
