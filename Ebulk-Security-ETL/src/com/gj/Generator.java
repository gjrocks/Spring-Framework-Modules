package com.gj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Security;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.thoughtworks.xstream.XStream;

public class Generator {

	 static {
	        Security.addProvider(new BouncyCastleProvider());
	 }
	
	public static void main(String[] args) throws Exception {

		if (args.length < 2) {
			System.out.println("Please provide the two parameters, one is folder location and other deployment name");
			return;
		}

		String folder = args[0];
		String deployment = args[1];

		new Generator().doIt(folder, deployment);
	}

	public void doIt(String folder, String deployment) throws Exception {

		Map<String, String> mp = new HashMap<>();

		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("/beans.xml"));
		Resource resource0 = new FileSystemResource(folder + "/" + deployment + "/runtime.properties");

		Resource resource1 = new FileSystemResource(folder + "/" + deployment + "/jdbc.properties");

		Resource resource2 = new ClassPathResource("etl-generator.properties");

		Resource resource3 = new ClassPathResource("etl-config.properties");
		Resource[] rs = new Resource[4];
		rs[0] = resource0;
		rs[1] = resource1;
		rs[2] = resource2;
		rs[3] = resource3;
		
		SpringPropertiesUtil util = new SpringPropertiesUtil();
		util.setLocations(rs);
		util.postProcessBeanFactory(factory);

		mp.putAll(util.getPropertiesMap());

		generateETLScript(mp);
		generateClassPath();
		generateETLScriptFromXMLConfig();
		Resource resource = new ClassPathResource("/Config.xml");
		String xmlStr=getResourceFileAsString_(resource.getInputStream());
		//StringBuilder etl = new StringBuilder("");
		Configuration t=(Configuration)fromXML(xmlStr);
		System.out.println(t.getConfigurations());
	}		
	
	public void generateETLScriptFromXMLConfig() throws Exception{
	/*	Config app=new Config();
		app.setTableName("crb_Applications");
		app.addCol(new Column("ca_id","java.lang.Long",true));
		app.addCol(new Column("ca_disclosure_result","java.lang.String",false));
		app.setSourceType("enc");
		app.setDestType("pln");
		String xml= getXML(app);
		System.out.println(xml);*/
		Resource resource = new ClassPathResource("/Config.xml");
		String xmlStr=getResourceFileAsString_(resource.getInputStream());
		StringBuilder etl = new StringBuilder("");
		Configuration t=(Configuration)fromXML(xmlStr);
		//System.out.println(t.getColumns());
		
		
		try {

			t.getConfigurations().forEach(obj -> {
				
				String table = obj.getTableName();
				//String table = tableN.substring(0, tableN.indexOf("("));
				String id = obj.getColumns().stream().filter(col->col.isPrimaryKey()).findFirst().get().getColumnName();
				List<Column> columns = obj.getColumns();
				Map<String, Column> col = new LinkedHashMap<>();
				
				for (Column column : columns) {
					
					if(!column.isPrimaryKey()){
				
					String cl = column.getColumnName();
					col.put(cl, column);
					}
					

				}

				String text = getETLScriptPerTableXML(table, col, id);
				etl.append(text);

				// System.out.println(text);
			});

			Path path = Paths.get("./resources/etl/etl_generated_xml.xml");
			try {
				String finalScript = getETLScript(etl.toString());
				Files.write(path, finalScript.getBytes());
				System.out.println("Please see the generated file::etl_generated.xml:: inside resources/etl folder.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	private void generateClassPath() {
		String folder = "./resources/lib";
		File f = new File(folder);

		String names[] = f.list();
		StringBuilder classpath = new StringBuilder(".");
		classpath.append(":").append(folder).append(":");
		for (String name : names) {

			if (name.endsWith(".jar")) {
				classpath.append(folder).append(name).append(":").append("./bin");
			}
		}
		System.out.println(classpath);
	}

	private void generateETLScript(Map<String, String> mp) throws Exception {

		Resource resource = new ClassPathResource("/etl-generator.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		StringBuilder etl = new StringBuilder("");
		/*
		 * String contents = new
		 * String(Files.readAllBytes(Paths.get("./resources/vanilla_cheqsdb.sql"
		 * ))); System.out.println("Contents (Java 7) : " + contents);
		 */
		
		Map<String, String> sortedMap = new TreeMap(props);
		try {

			sortedMap.entrySet().forEach(obj -> {
				Map<String, String> col = new LinkedHashMap<>();
				String tableN = obj.getKey().toString();
				String table = tableN.substring(0, tableN.indexOf("("));
				String id = tableN.substring(tableN.indexOf("(") + 1, tableN.length() - 1);
				String[] columns = obj.getValue().toString().split(",");

				for (int i = 0; i < columns.length; i++) {
					String cl = columns[i];
					String onlyColumn = cl.substring(0, cl.indexOf("("));
					if (cl.indexOf("text") != -1) {
						col.put(onlyColumn, "java.sql.Clob");

					}
					if (cl.indexOf("varchar") != -1) {
						col.put(onlyColumn, "java.lang.String");
					}

				}

				String text = getETLScriptPerTable(table, col, id);
				etl.append(text);

				// System.out.println(text);
			});

			Path path = Paths.get("./resources/etl/etl_generated.xml");
			try {
				String finalScript = getETLScript(etl.toString());
				Files.write(path, finalScript.getBytes());
				System.out.println("Please see the generated file::etl_generated.xml:: inside resources/etl folder.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	private String getETLScriptPerTable(String table,Map<String,String> col,String id) {
		try {
			VelocityEngine ve = new VelocityEngine();

			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("table", table);
			context.put("cols", col);
			context.put("id",id);
			 Template t = ve.getTemplate("./resources/etl/etl.vm");
		        StringWriter writer = new StringWriter();
		        t.merge(context, writer);
		       
		        return writer.toString();
		        
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private String getETLScriptPerTableXML(String table,Map<String,Column> col,String id) {
		try {
			VelocityEngine ve = new VelocityEngine();

			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("table", table);
			context.put("cols", col);
			context.put("id",id);
			 Template t = ve.getTemplate("./resources/etl/etl_xml.vm");
		        StringWriter writer = new StringWriter();
		        t.merge(context, writer);
		       
		        return writer.toString();
		        
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private String getETLScript(String etlTextForAllTables) {
		try {
			VelocityEngine ve = new VelocityEngine();

			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("etlTextForAllTables",etlTextForAllTables);
			 Template t = ve.getTemplate("./resources/etl/etl_entire.vm");
		        StringWriter writer = new StringWriter();
		        t.merge(context, writer);
		       
		        return writer.toString();
		        
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties getProperties(String folder,String deployment,String name){
		Properties props=new Properties();
		try {
			props.load(new FileReader(new File(folder+"/"+deployment+"/"+name)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public Map<String,String> getMap(Properties properties){
		final Map<String,String> mp=new HashMap<>();
		properties.forEach((key,value)->{
			mp.put(key.toString(), value.toString());
		});
		
		return mp;
	}
	
	  public static String getXML(Object obj){
	        XStream xstrm = new XStream();
	        xstrm.alias("Config",Config.class);
	       xstrm.alias("Column", com.gj.Column.class);
	        String xml = xstrm.toXML(obj);
	    
	        return xml;
	 }
	  
	  
	  public Object fromXML(String xml) {
			if (xml != null) {
				 XStream xstrm = new XStream();
				  
				 xstrm.alias("Configuration",Configuration.class);
				 xstrm.alias("Config",Config.class);
			        xstrm.alias("Column", com.gj.Column.class);
			     
				return xstrm.fromXML(xml);
			} else {
				return null;
			}
		}
	  
	  public String getResourceFileAsString(String fileName) {
		    InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		    if (is != null) {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		    }
		    return null;
		}
	  
	  public String getResourceFileAsString_(InputStream is) {
		   
		    if (is != null) {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		    }
		    return null;
		}
}


