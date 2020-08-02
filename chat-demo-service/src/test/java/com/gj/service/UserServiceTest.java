package com.gj.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gj.model.User;
import com.gj.repository.UserRepository;
import com.gj.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllUser(){
		List<User> UserList = new ArrayList<User>();
		UserList.add(new User(1,"User Sample 1",true));
		UserList.add(new User(2,"User Sample 2",true));
		UserList.add(new User(3,"User Sample 3",false));
		when(userRepository.findAll()).thenReturn(UserList);
		
		List<User> result = userService.getAllUser();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetUserById(){
		User User = new User(1,"User Sample 1",true);
		when(userRepository.findOne(1L)).thenReturn(User);
		User result = userService.getUserById(1);
		assertEquals(1, result.getId());
		assertEquals("User Sample 1", result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void saveUser(){
		User User = new User(8,"User Sample 8",true);
		when(userRepository.save(User)).thenReturn(User);
		User result = userService.saveUser(User);
		assertEquals(8, result.getId());
		assertEquals("User Sample 8", result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void removeUser(){
		User User = new User(8,"User Sample 8",true);
		userService.removeUser(User);
        verify(userRepository, times(1)).delete(User);
	}
	
	

}

