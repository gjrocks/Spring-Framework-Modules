package com.gj.es.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {

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
	static	long  count=1;
	static BufferedWriter wt=null;
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
		wt=new BufferedWriter(new FileWriter(new File("c:\\alm\\final2.json")));
		Map<String, Integer> itrmap=new LinkedHashMap<>();
	Date dt=new DateWrapper(1, 1, 2019).getDate();
	int itr=0;	
   Map<Integer,List<String>> it=new LinkedHashMap<Integer,List<String>>();
   List<String> temp=null;
	for(int i=0;i<365;i++) {
			Date r=DateWrapper.addDays(dt, i);
	        if(i%15==0) {
	        	itr++;
	        }
			itrmap.put(DateWrapper.convertDateToString(r),itr);
			if(it.get(itr)==null) {
				temp=new ArrayList<String>();
				temp.add(DateWrapper.convertDateToString(r));
				it.put(itr, temp);
			}else {
				temp=it.get(itr);
				temp.add(DateWrapper.convertDateToString(r));
				it.put(itr, temp);
			}
		}
      	System.out.println(it);	
      	//create(itrmap);
      	for(int i=1;i<20;i++) {
      	createForItr(i,it);
      	}
      	wt.close();
	}
	
	public static Date getDevlDate(Date d) {
		DateWrapper dr=new DateWrapper(DateWrapper.addDays(d,randInt(2, 30)));
	    dr.setTime(randInt(0, 23), randInt(0,59), randInt(0, 59)); 
	    return dr.getDate();
	    
	}
	
	public static Date getQualDate(Date d) {
		DateWrapper dr=new DateWrapper(DateWrapper.addDays(d,randInt(2, 10)));
	    dr.setTime(randInt(0, 23), randInt(0,59), randInt(0, 59)); 
	    return dr.getDate();
	    
	}
	
	public static Date getProdDate(Date d) {
		DateWrapper dr=new DateWrapper(DateWrapper.addDays(d,randInt(2, 20)));
	    dr.setTime(randInt(0, 23), randInt(0,59), randInt(0, 59)); 
	    return dr.getDate();
	    
	}
	private static void createForItr(int itr, Map<Integer, List<String>> it) throws Exception{
	  
		List<String> liDates=it.get(itr);
		Date startDate=DateWrapper.convertStringToDate(liDates.get(0));
		
		List<String> us=getUS();
		List<String> d=getDE();
		List<String> q=getQ();
		
		int maxNumberOfWork=8;
		int minNumberOfWork=3;
		int work=randInt(minNumberOfWork, maxNumberOfWork);
		
		int y =work/2;
		List<Work> wk=new ArrayList<Work>();
		for(int i=0;i<y;i++) {
			Work wr=new Work();
			wr.setId(++count);
			wr.setWorkSource("Rally");
			wr.setPriority("P" +randInt(1, 4));
			wr.setSprint("ITR-"+itr);
			String [] item=us.get(randInt(1, us.size()-1)).split(",");
			wr.setWorkDesc(item[1]);
			wr.setWorkID(item[0]);
			wr.setWorkType("User_Story");
		    int createDelay=randInt(1, 90);

		    Date weekcreation=DateWrapper.addDays(startDate, -createDelay);
		    wr.setWork_creation_date(weekcreation);
		    wr.setWork_inception_date(startDate);
		    wr.setDevl_deploy_date(getDevlDate(startDate));
		    wr.setQual_deploy_date(getQualDate(wr.getDevl_deploy_date()));
		    wr.setProd_deploy_date(getProdDate(wr.getQual_deploy_date()));
		    wr.setCommitId(getRandomAlphanumericString(12));
		    wr.setGitRepoName("Alfa");
		    wr.setSprint_start_date(startDate);
		    wr.setSprint_end_date(DateWrapper.addDays(wr.getSprint_start_date(),14));
		    wk.add(wr);
		}
		int randQ=randInt(1, (work-y));
		for(int j=0;j<randQ;j++) {
			Work wr=new Work();
			wr.setId(++count);
			wr.setWorkSource("Sonar");
			wr.setPriority("P" +randInt(1, 4));
			wr.setSprint("ITR-"+itr);
			String [] item=q.get(randInt(1, q.size()-1)).split(",");
			wr.setWorkDesc(item[1]);
			wr.setWorkID(item[0]);
			wr.setWorkType("Quality");
		    int createDelay=randInt(1, 90);

		    Date weekcreation=DateWrapper.addDays(startDate, -createDelay);
		    wr.setWork_creation_date(weekcreation);
		    wr.setWork_inception_date(startDate);
		    wr.setSprint_start_date(startDate);
		    wr.setSprint_end_date(DateWrapper.addDays(wr.getSprint_start_date(),14));
		    
		    wr.setDevl_deploy_date(getDevlDate(startDate));
		    wr.setQual_deploy_date(getQualDate(wr.getDevl_deploy_date()));
		    wr.setProd_deploy_date(getProdDate(wr.getQual_deploy_date()));
		    wr.setCommitId(getRandomAlphanumericString(12));
		    wr.setGitRepoName("Alfa");
		    
		    wk.add(wr);
		}
		
		for(int j=0;j<randQ;j++) {
			Work wr=new Work();
			wr.setId(++count);
			wr.setWorkSource("Sonar");
			wr.setPriority("P" +randInt(1, 4));
			wr.setSprint("ITR-"+itr);
			String [] item=d.get(randInt(1, d.size()-1)).split(",");
			wr.setWorkDesc(item[1]);
			wr.setWorkID(item[0]);
			wr.setWorkType("Defect");
		    int createDelay=randInt(1, 90);

		    Date weekcreation=DateWrapper.addDays(startDate, -createDelay);
		    wr.setWork_creation_date(weekcreation);
		    wr.setWork_inception_date(startDate);
		    wr.setSprint_start_date(startDate);
		    wr.setSprint_end_date(DateWrapper.addDays(wr.getSprint_start_date(),14));
		    wr.setDevl_deploy_date(getDevlDate(startDate));
		    wr.setQual_deploy_date(getQualDate(wr.getDevl_deploy_date()));
		    wr.setProd_deploy_date(getProdDate(wr.getQual_deploy_date()));
		    wr.setCommitId(getRandomAlphanumericString(12));
		    wr.setGitRepoName("Alfa");
		    wk.add(wr);
		}
		
		for(int i=0;i<wk.size();i++) {
			Work wr=wk.get(i);
			ObjectMapper objectMapper = new ObjectMapper();
			//2020-06-21T16:30:00.000
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			objectMapper.setDateFormat(df);
			wt.write("{\"index\":{\"_id\":\""+wr.getId()+"\"}}");
			wt.write("\n");
		    wt.write(objectMapper.writeValueAsString(wr));
		    wt.write("\n");
			System.out.println();
		}
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
