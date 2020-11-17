package com.gj.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gj.UserApplication;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	//List<User> todos=Arrays.asList(new User(1, "aws", false),new User(1, "azure", false),new User(1, "aws", false),new User(1, "aws", false));
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
         
	}

	@Test
	public void verifyAllUserList() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	/*@Test
	public void verifySaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"text\" : \"New User Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.text").value("New User Sample"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	@Test
	public void verifyUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.text").value("Build the artifacts"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User doesnt exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User doesnt exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("User has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/8").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User to delete doesnt exist"))
		.andDo(print());
	}
	
	
	
	
	@Test
	public void verifyMalformedSaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"text\" : \"New User Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"1\", \"text\" : \"New User Text\", \"completed\" : \"false\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.text").value("New User Text"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"idd\": \"8\", \"text\" : \"New User Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User to update doesnt exist"))
		.andDo(print());
	}
*/
}
