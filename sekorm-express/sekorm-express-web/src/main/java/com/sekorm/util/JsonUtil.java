package com.sekorm.util;

import java.util.HashMap;
import java.util.List;

public class JsonUtil {
	
	public static String voToString(PageUtil page,List<HashMap<String,String>> list){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"recordsFiltered\":\""+page.getTotle()+"\",");
		sb.append("\"recordsTotal\":\""+page.getTotle()+"\",");
		sb.append( "\"data\": [");
		for(HashMap<String,String> hm:list){
			sb.append("{");
			sb.append( "\"id\":\""+hm.get("LINE")+"\",");
			sb.append( "\"kh\":\""+hm.get("CUSTOMER_NAME")+"\",");
			sb.append( "\"uuid\":\""+hm.get("TRACKING_UUID")+"\",");
			sb.append( "\"jhh\":\""+hm.get("SOURCE_ID")+"\",");
			sb.append( "\"reference\":\""+hm.get("REFERENCE_STRING")+"\",");
			sb.append( "\"sjdz\":\""+hm.get("RECEIVER_ADDRESS")+"\",");
			sb.append( "\"sjr\":\""+hm.get("RECEIVER_NAME")+"\",");
			sb.append( "\"sjrdh\":\""+hm.get("RECEIVER_PHONE_NO")+"\",");
			sb.append( "\"jhrq\":\""+hm.get("SHIP_DATE")+"\",");
			sb.append( "\"kdgs\":\""+hm.get("KD_COMPANY")+"\",");
			sb.append( "\"kddh\":\""+hm.get("KD_NO")+"\",");
			sb.append( "\"zt\":\""+hm.get("MEANING")+"\",");
			sb.append( "\"ztrq\":\""+hm.get("LAST_UPDATE_DATE")+"\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]}");
		return sb.toString();
	}
	
	public static String usersVoToString(PageUtil page,List<HashMap<String,String>> list){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"recordsFiltered\":\""+page.getTotle()+"\",");
		sb.append("\"recordsTotal\":\""+page.getTotle()+"\",");
		sb.append( "\"data\": [");
		for(HashMap<String,String> hm:list){
			sb.append("{");
			sb.append( "\"nu\":\""+String.valueOf(hm.get("NU"))+"\",");
			sb.append( "\"username\":\""+hm.get("USERNAME")+"\",");
			sb.append( "\"role\":\""+hm.get("ROLE")+"\",");
			sb.append( "\"id\":\""+hm.get("ID")+"\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]}");
		return sb.toString();
	}
	
	public static String logVoToString(PageUtil page,List<HashMap<String,String>> list){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"recordsFiltered\":\""+page.getTotle()+"\",");
		sb.append("\"recordsTotal\":\""+page.getTotle()+"\",");
		sb.append( "\"data\": [");
		for(HashMap<String,String> hm:list){
			sb.append("{");
			sb.append( "\"NU\":\""+String.valueOf(hm.get("NU"))+"\",");
			sb.append( "\"UPDATE_NO\":\""+String.valueOf(hm.get("UPDATE_NO"))+"\",");
			sb.append( "\"UPDATE_USER\":\""+String.valueOf(hm.get("UPDATE_USER"))+"\",");
			sb.append( "\"UPDATE_STATUS\":\""+String.valueOf(hm.get("UPDATE_STATUS"))+"\",");
			sb.append( "\"CREATE_DATE\":\""+String.valueOf(hm.get("CREATE_DATE"))+"\",");
			sb.append( "\"OLD_STATUS\":\""+String.valueOf(hm.get("OLD_STATUS"))+"\",");
			sb.append( "\"OLD_DATE\":\""+String.valueOf(hm.get("OLD_DATE"))+"\",");
			sb.append( "\"UPDATE_DATE\":\""+String.valueOf(hm.get("UPDATE_DATE"))+"\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]}");
		return sb.toString();
	}
}
