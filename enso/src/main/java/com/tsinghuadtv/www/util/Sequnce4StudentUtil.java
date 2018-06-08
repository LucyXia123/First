package com.tsinghuadtv.www.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sequnce4StudentUtil {
	
   private static String key="0000";
   
   private static synchronized String getNextKey() {
	   String retStr="";
	   int keyIntValue =Integer.parseInt(key);
	   key=String.valueOf(keyIntValue+1);
	   if (keyIntValue<1000) {
		   retStr=key;
		   for(int i=0;i<4-key.length();i++){
			   retStr="0"+retStr;
		   }
		   key=retStr;
	   }
	   if (Long.parseLong(key)>9999) {
		   key="0001";
       }
	   return key;
   }
   public static String getMerchantSeq() {
	   return "STU"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+getNextKey();
   }
}
