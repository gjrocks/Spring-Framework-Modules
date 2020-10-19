package com.gj.es.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader2 {

	public static Properties data=new Properties();
	public static String[] titles=null;
	public static String[] fnames=null;
	public static String[] lnames=null;
	public static String[] mnames=null;
	public static String[] rnames=null;
	public static String[] rnames2=null;
	public static String[] housenames=null;
	public static String[] city=null;
	public static String[]  company=null;
	public static String[]  courses=null;
	public static String[]  coursesduration=null;
	public static String dummy="this is test";
	
	static String  types [] = new String[]{"Defect","Problem","User_Story","Quality","Security"};
	public static Date getDateBetweenTwoDates(Date one, Date two) {
		return null;
	}
	
	public static String randomString(int targetStringLength) {
		
		   int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		   // int targetStringLength = 10;
		    Random random = new Random();
		 
		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		return generatedString;
	}
	
	public static String getRandomAlphanumericString(int targetStringLength ) {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	  //  int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 return generatedString;
	    //System.out.println(generatedString);
	}
	public static int getRandomInt(int from, int to) {
		return 0;
	}
	
	public static String getWorkType() {
		int pos=randInt(0, types.length-1);
		return types[pos];
	}
	
   public static List<String> getUS(){
	   List<String> us=new ArrayList<String>();
	   try {
		   //FileReader rd=null;
		   BufferedReader ty=new BufferedReader(new FileReader("c:\\alm\\us.csv"));
		   String temp=null;
		   while((temp=ty.readLine())!=null) {
			   us.add(temp);
		   }
		   ty.close();
		  
	   }catch(Exception e) {
		   
	   }
	   return us;
   
   }
   
   public static List<String> getDE(){
	   List<String> us=new ArrayList<String>();
	   try {
		   //FileReader rd=null;
		   BufferedReader ty=new BufferedReader(new FileReader("c:\\alm\\d.csv"));
		   String temp=null;
		   while((temp=ty.readLine())!=null) {
			   us.add(temp);
		   }
		   ty.close();
		  
	   }catch(Exception e) {
		   
	   }
	   return us;
   }
 
   
   public static List<String> getQ(){
	   List<String> us=new ArrayList<String>();
	   try {
		   //FileReader rd=null;
		   BufferedReader ty=new BufferedReader(new FileReader("c:\\alm\\q.csv"));
		   String temp=null;
		   while((temp=ty.readLine())!=null) {
			   us.add(temp);
		   }
		   ty.close();
		  
	   }catch(Exception e) {
		   
	   }
	   return us;
   }
   
	public static void main(String[] args)  throws Exception{
		
		Map<String, Integer> itrmap=new LinkedHashMap<>();
	Date dt=new DateWrapper(1, 1, 2019).getDate();
	int itr=0;	

	for(int i=0;i<365;i++) {
			Date r=DateWrapper.addDays(dt, i);
	        if(i%15==0) {
	        	itr++;
	        }
			itrmap.put(DateWrapper.convertDateToString(r),itr);
		}
      	System.out.println(itrmap);	
      	create(itrmap);
	}
	
	public static void create(Map<String,Integer> mp)  throws Exception{
		
		System.out.println("ganesh");
		
		Work wr=new Work();
		wr.setId(1L);
		wr.setWorkSource("Rally");
		wr.setPriority("P1");
		wr.setSprint("ITR-21");
		wr.setWorkDesc("Feature-11212 : Some feature");
		wr.setWorkID("F11221");
		wr.setWorkType("Feature");
		Date creation_date=getRandomDate(2019, 1);
		wr.setWork_creation_date(creation_date);
		int sprint=mp.get(DateWrapper.convertDateToString(creation_date));
	    int inceptDelay=randInt(1, 90);

	    Date weekInception=DateWrapper.addDays(creation_date, inceptDelay);
	    
		wr.setWork_inception_date(weekInception);
		int sprint_inception=mp.get(DateWrapper.convertDateToString(weekInception));
		for (Map.Entry<String, Integer> en : mp.entrySet()) {
			if(en.getValue()==sprint_inception) {
				wr.setSprint_start_date(DateWrapper.convertStringToDate(en.getKey()));
				wr.setSprint_end_date(DateWrapper.addDays(wr.getSprint_start_date(),14));
				break;
			}
		}
		
		
		wr.setSprint("ITR_"+sprint_inception);
		ObjectMapper objectMapper = new ObjectMapper();
		//2020-06-21T16:30:00.000
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		objectMapper.setDateFormat(df);
		System.out.println(objectMapper.writeValueAsString(wr));
	}

	
	



	private static Object getRandomBoolean() {
	int r=randInt(0, 1);
	if(r==0)
		return false;
	return true;
	}

	public static int getWeekFromDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
	public static Date getStartOfTheWeek(int week) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, 23);        
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();  
	}

	private static void prepareData(Properties data2) {
	
		titles=data2.getProperty("titles").split(",");
		 fnames=data2.getProperty("fnames").split(",");;
		 lnames=data2.getProperty("lnames").split(",");
		 mnames=data2.getProperty("mnames").split(",");
		 rnames=data2.getProperty("rnames").split(",");
		 rnames2=data2.getProperty("rnames2").split(",");
		 company=data2.getProperty("company").split(",");
		 city=data2.getProperty("city").split(",");
		 housenames=data2.getProperty("housenames").split(",");
		 courses=data2.getProperty("courses").split(",");
		 coursesduration=data2.getProperty("coursesduration").split(",");
		//public static String[]  courses=null;
			//public static String[]  coursesduration=null;
	}



	
	
	public static java.util.Date getRandomDate(int year, int month) {

		DateWrapper dt=new DateWrapper(randInt(1, 28), month, year);
		dt.setTime(randInt(0, 23), randInt(0, 59), randInt(0, 59));
		//Calendar calendar = new GregorianCalendar();

		//calendar.set(Calendar.YEAR, year);
		//calendar.set(Calendar.MONTH, month); // 11 = december
		//calendar.set(Calendar.DAY_OF_MONTH, randInt(1, 28)); // christmas eve
		//java.util.Date dt=calendar.getTime();
		return dt.getDate();
	}

	
	
	
	public static java.sql.Date getRandomDate() {
		
		Calendar calendar = new GregorianCalendar();

		calendar.set(Calendar.YEAR, randInt(2001, 2014));
		calendar.set(Calendar.MONTH, randInt(1, 11)); // 11 = december
		calendar.set(Calendar.DAY_OF_MONTH, randInt(1, 28)); // christmas eve
		java.util.Date dt=calendar.getTime();
		return new java.sql.Date(dt.getTime());
	}



	public static String getRandomVal(String[] str) {
	
		int rnt=randInt(0,str.length-1);
		return str[rnt];
	}



	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
}
