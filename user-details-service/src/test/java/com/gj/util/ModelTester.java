package com.gj.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.gj.exception.UserException;
import com.gj.model.Response;
import com.gj.model.User;
import com.gj.repository.UserRepository;
import com.gj.service.UserServiceImpl;

public class ModelTester {

	
	@Mock
	UserRepository repos;
	
	@Autowired
	@InjectMocks
	UserServiceImpl service;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		BDDMockito.given(repos.findOne(1L)).willReturn(new User(1,"fname", "lname", "address", "userid", "mobileNumber", "email@email.com"));
		//this wont work please see the link https://stackoverflow.com/questions/3762047/throw-checked-exceptions-from-mocks-with-mockito
		//in simple words, findOne method does not trow todoException, so u have to throw runtimeException
		//BDDMockito.given(repos.findOne(2L)).willThrow(new UserException("Problem in getting TODO"));
		//following is one more way we can throw Exception
		BDDMockito.given(repos.findOne(2L)).willAnswer(answer-> {throw new UserException("Problem in getting TODO");});
	}
	
	@Test
	public void testResponse(){
		Response res=new Response(0,"test");
		assertEquals(0, res.getStatus());
		assertEquals("test",res.getMessage());
	}
	
	@Test(expected=NullPointerException.class)
	public void testException(){
		Response res=null;
				res.getMessage();
				
	}
	
	@Test
	public void testUserService(){
		
	//	Mockito.when(repos.findOne(1L)).thenReturn(new User(1,"test",false));
		User temp=service.getUserById(1L);            
		assertEquals("fname",temp.getFname());
		Mockito.verify(repos,Mockito.atLeastOnce()).findOne(Mockito.anyLong());
		//use this in case you 
		Throwable t=catchThrowable((()->service.getUserById(2L)));
		
		assertThatThrownBy(()->service.getUserById(2L)).isExactlyInstanceOf(UserException.class);
		
	}
	
}
