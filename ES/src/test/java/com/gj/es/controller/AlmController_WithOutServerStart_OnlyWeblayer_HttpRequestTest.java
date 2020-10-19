package com.gj.es.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.es.model.Work;

 
//sanity test check the logs it does not load all the application context and it does not start the server.
//it is the unit test for the controller/web layer
//for multiple controllers application provide the name of the controller here
//mock all the dependencies used in the controller
//this is unit test for the controllers
@WebMvcTest(AlmController.class)
public class AlmController_WithOutServerStart_OnlyWeblayer_HttpRequestTest {

	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testHeartBeat() throws Exception{
		
		
		  this.mock.perform(get("/alm/ping")).andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(content().string(containsString("ok")));
		 
	}
	public Work getWork() {
		Work wr=new Work();
		wr.setId(145L);
		wr.setWorkID("US12345");
		return wr;
	}
	
	public String getSerialisedWork(Work wr) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		//2020-06-21T16:30:00.000
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		objectMapper.setDateFormat(df);
		return objectMapper.writeValueAsString(wr);
	}
	@Test
	public void testWorkPost() throws Exception{
		
		
		  this.mock.perform(post("/alm/work")
				           .contentType(MediaType.APPLICATION_JSON)
				           .content(getSerialisedWork(getWork()))
				          // .accept(MediaType.APPLICATION_JSON)
		  ).andDo(print())
		  .andExpect(status().isOk());
		 // .andExpect(content().string(containsString("ok")));
		 
	}
}
