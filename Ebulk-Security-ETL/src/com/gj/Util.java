package com.gj;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Util {

	 public static AtomicInteger cnt=new AtomicInteger(0);
	 public static AtomicLong lng=new AtomicLong(0);
	 public static Map<Long,String> data=new HashMap<Long,String>();
	public static void increment() {
		cnt.incrementAndGet();
		
      System.out.println(cnt);
	}
	
	public static void addEncryptionTime(long time) {
		lng.addAndGet(time);
   }
	public static AtomicLong getLng() {
		return lng;
	}

	public static void setLng(AtomicLong lng) {
		Util.lng = lng;
	}

	public static AtomicInteger getCnt() {
		return cnt;
	}
	public static void setCnt(AtomicInteger cnt) {
		Util.cnt = cnt;
	}

	public static String convertClobToString(Clob clob) throws IOException, SQLException {
		if(clob==null)
			return null;
        Reader reader = clob.getCharacterStream();
        int c = -1;
        StringBuilder sb = new StringBuilder();
        while((c = reader.read()) != -1) {
             sb.append(((char)c));
        }

        return sb.toString();
 }
	
	public static String convertToString(Object obj,String type) throws IOException, SQLException {
		if(type==null || obj==null)
			return null;
		if(type.equalsIgnoreCase("java.sql.Clob"))
		{
			java.sql.Clob temp=(java.sql.Clob)obj;
			return convertClobToString(temp);
		}
        return obj.toString();
 }
	
	public static Map<Long, String> getData() {
		return data;
	}
	public static void setData(Map<Long, String> data) {
		Util.data = data;
	}
	
	
	public static void addData(Long id, String text){
		data.put(id,text);
	}
	
	public static String getData(Long id){
		return data.get(id);
	}
}



