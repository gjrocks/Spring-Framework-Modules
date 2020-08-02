package com.gj;


import java.io.StringReader;
import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gj.model.Person;

public class IT_PersonRS {

	public static StringBuilder urlBase=null;
	
	@Before
	public void init(){
		urlBase=new StringBuilder("http://localhost:8080/jaxrs-cxf/person");
	}
	
	
	@Ignore
	@Test
	public void it_getPerson_statusCode_ok(){
		String servicePath="/getPerson/";
		try{
		Integer data=1;
		urlBase.append(servicePath);
		String url=urlBase.append(URLEncoder.encode(data.toString(),"UTF-8")).toString();
		HttpClient client = new HttpClient();
        //PostMethod mPost = new PostMethod(url);
		GetMethod mGet=new GetMethod(url);
        mGet.addRequestHeader(getHttpHeader());
        client.executeMethod(mGet);
        //asserts for return status code
        Assert.assertEquals(HttpStatus.SC_OK, mGet.getStatusCode());
        //get output marshall it an
        String output = mGet.getResponseBodyAsString( );
        mGet.releaseConnection( );
        System.out.println("out : " + output);
        
        
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void it_getPerson_dataTest_nullEmptyCheck(){
		String servicePath="/getPerson/";
		try{
		Integer data=1;
		urlBase.append(servicePath);
		String url=urlBase.append(URLEncoder.encode(data.toString(),"UTF-8")).toString();
		HttpClient client = new HttpClient();
        //PostMethod mPost = new PostMethod(url);
		GetMethod mGet=new GetMethod(url);
        mGet.addRequestHeader(getHttpHeader());
        client.executeMethod(mGet);
        String output = mGet.getResponseBodyAsString( );
        mGet.releaseConnection( );
        System.out.println("out : " + output);
        //asserts for output not null
        Assert.assertNotNull(output);
        //asserts that output is not empty
        Assert.assertNotEquals("", output.trim());
        
        if(output.indexOf("?>")!=-1){
        	System.out.println(output.substring(output.indexOf("?>")+2));
        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void it_getPerson_dataTest_fieldTest(){
		String servicePath="/getPerson/";
		try{
		Integer data=1;
		urlBase.append(servicePath);
		String url=urlBase.append(URLEncoder.encode(data.toString(),"UTF-8")).toString();
		HttpClient client = new HttpClient();
        //PostMethod mPost = new PostMethod(url);
		GetMethod mGet=new GetMethod(url);
       
        mGet.addRequestHeader(getHttpHeader());
        client.executeMethod(mGet);
       //get output marshall it an
        //String output = mGet.getResponseBodyAsString( );
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser(mGet.getResponseBodyAsStream());
        Person p = parser.readValueAs(Person.class);
        mGet.releaseConnection( );
       
        //asserts for output not null
        Assert.assertNotNull(p);
        
        //assert that ids are same
        Assert.assertEquals(data, new Integer(p.getId()));
        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Header getHttpHeader() {
		 Header mtHeader = new Header();
	        mtHeader.setName("content-type");
	        mtHeader.setValue("application/x-www-form-urlencoded");
	        mtHeader.setName("Accept");
	        mtHeader.setValue("application/xml");
	        return mtHeader;
	}
	
	@Ignore
	@Test
	public void it_addPerson(){
		
		String servicePath="/add";
		try{
		urlBase.append(servicePath);
		String url=urlBase.toString();
		HttpClient client = new HttpClient();
        PostMethod mPost = new PostMethod(url);
		mPost.addRequestHeader(getHttpHeader());
       
		NameValuePair[] urlParameters = new NameValuePair[6];
		urlParameters[0]=new NameValuePair("id", "3");
		urlParameters[1]=new NameValuePair("fname", "ganesh1");
		urlParameters[2]=new NameValuePair("lname", "jadhav1");
		urlParameters[3]=new NameValuePair("sal", "23445451");
		urlParameters[4]=new NameValuePair("age", "351");
		urlParameters[5]=new NameValuePair("dob", new java.util.Date().getTime()+"");
		
		//post.setEntity(new UrlEncodedFormEntity(urlParameters));
		mPost.setRequestBody(urlParameters);
		 client.executeMethod(mPost);
		 Assert.assertEquals(HttpStatus.SC_OK, mPost.getStatusCode());
		 System.out.println(mPost.getResponseBodyAsString());
		 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test 
	public void it_getPerson_dataTest_jaxb(){
		String servicePath="/getPerson/";
		try{
		Integer data=1;
		urlBase.append(servicePath);
		String url=urlBase.append(URLEncoder.encode(data.toString(),"UTF-8")).toString();
		HttpClient client = new HttpClient();
        //PostMethod mPost = new PostMethod(url);
		GetMethod mGet=new GetMethod(url);
        mGet.addRequestHeader(getHttpHeader());
        client.executeMethod(mGet);
        String output = mGet.getResponseBodyAsString( );
        mGet.releaseConnection( );
        System.out.println("out : " + output);
        //asserts for output not null
        Assert.assertNotNull(output);
        //asserts that output is not empty
        Assert.assertNotEquals("", output.trim());
        
        /*if(output.indexOf("?>")!=-1){
        	System.out.println(output.substring(output.indexOf("?>")+2));
        }*/
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Person user = (Person) jaxbUnmarshaller.unmarshal(new StringReader(output));
        
        System.out.println(user.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * client using jax-rs client API , CXF implementation
	 */
	@Test
	public void it_getPerson_using_jaxrsClient_API(){
		
		
		try{
			Client client = ClientBuilder.newClient();
			//client.getConfiguration().
			client.register(LoggingClientFilter.class);
			client.register(CustomClientInterceptor.class); //entity interceptor
			WebTarget target = client.target("http://localhost:8080/jaxrs-cxf/person/getPerson/");
			target = target.path("1");
			
			Invocation.Builder builder = target.request();
			Response response = builder.get();
			
			Person b = builder.get(Person.class);
			System.out.println(b.toString());
			//Person k=(Person)response.getEntity();
			//System.out.println(k);
		}catch(Exception r){
			r.printStackTrace();
		}
	}
	
}
