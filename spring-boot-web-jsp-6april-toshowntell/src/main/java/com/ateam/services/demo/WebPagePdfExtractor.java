package com.ateam.services.demo;

/*import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;*/

public class WebPagePdfExtractor {

	
	
  /*  public Map<String, Object> processRecordFromFile(String url) {
       
        Map<String, Object> map = new HashMap<String, Object>();
        try {
           
            InputStream input = null;
           
                try {
                    input = new FileInputStream(new File(url) );
                    BodyContentHandler handler = new BodyContentHandler();
                    Metadata metadata = new Metadata();
                    AutoDetectParser parser = new AutoDetectParser();
                    ParseContext parseContext = new ParseContext();
                    parser.parse(input, handler, metadata, parseContext);
                    map.put("text", handler.toString().replaceAll("\n|\r|\t", " "));
                    map.put("title", metadata.get(TikaCoreProperties.TITLE));
                    map.put("pageCount", metadata.get("xmpTPg:NPages"));
                   // map.put("status_code", response.getStatusLine().getStatusCode() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
           
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return map;
}
    
    public Map<String, Object> processRecordFromFile(InputStream input) {
        
        Map<String, Object> map = new HashMap<String, Object>();
        try {
           
          
           
                try {
                   
                    BodyContentHandler handler = new BodyContentHandler();
                    Metadata metadata = new Metadata();
                    AutoDetectParser parser = new AutoDetectParser();
                    ParseContext parseContext = new ParseContext();
                    parser.parse(input, handler, metadata, parseContext);
                    map.put("text", handler.toString().replaceAll("\n|\r|\t", " "));
                    map.put("title", metadata.get(TikaCoreProperties.TITLE));
                    map.put("pageCount", metadata.get("xmpTPg:NPages"));
                   // map.put("status_code", response.getStatusLine().getStatusCode() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
           
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return map;
}
    
    public Map<String, Object> processRecord(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream input = null;
            if (entity != null) {
                try {
                    input = entity.getContent();
                    BodyContentHandler handler = new BodyContentHandler();
                    Metadata metadata = new Metadata();
                    AutoDetectParser parser = new AutoDetectParser();
                    ParseContext parseContext = new ParseContext();
                    parser.parse(input, handler, metadata, parseContext);
                    map.put("text", handler.toString());
                    map.put("title", metadata.get(TikaCoreProperties.TITLE));
                    map.put("pageCount", metadata.get("xmpTPg:NPages"));
                    map.put("status_code", response.getStatusLine().getStatusCode() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return map;
}

public static void main(String arg[]) {
    WebPagePdfExtractor webPagePdfExtractor = new WebPagePdfExtractor();
    File file =new File("C:\\temp\\output\\pdf");
    List<String> filesProcessed=new ArrayList<>();
    while(true){
    	
    	try{
    	   file.listFiles();
    	   for(File f:file.listFiles()){
    		   if(!filesProcessed.contains(f.getName())){
    			   Map<String, Object> extractedMap = webPagePdfExtractor.processRecordFromFile(f.getPath());
    			   String text=extractedMap.get("text").toString();
    			   writetoTextFile(text,f.getName());
    			   filesProcessed.add(f.getName());
    		   }
    	   }
    	}catch(Exception e){
    		
    	}
    }
    
   // Map<String, Object> extractedMap = webPagePdfExtractor.processRecordFromFile("C:\\GANESH-PERSONAL\\ganesh-jadhav.pdf");
   
}
    private static void writetoTextFile(String text, String name) {
    	 File file =new File("C:\\temp\\output\\text\\");
	try{
		 Path path = Paths.get(file + name.replace(".pdf", ".txt"));
         Files.write(path, text.getBytes());
	}catch(Exception e){
		
	}
	
}*/

	public static String extract(String search,String str)
    {
      //String search = "Total Toys";
      //String str = "This is something Total Toys 300,000.00 49,999.00 This is something";
      int index = str.indexOf(search);
      index += search.length();
      String[] tokens = str.substring(index).trim().split(" ");
      String val1 = tokens[0];
      return val1.trim();
    }
	
	public static String[] extractHSBC(String search,String str)
	{
	  //String search = "Total Toys";
	  //String str = "This is something Total Toys 300,000.00 49,999.00 This is something";
	  int index = str.indexOf(search);
	  index = index-2;
	  String[] tokens = str.substring(index).split(" ");
	  return tokens;
	}
}