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

import com.gj.model.UserProject;
import com.gj.repository.UserProjectsRepository;
import com.gj.service.UserProjectsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProjectServiceTest {
	
	@Mock
	private UserProjectsRepository userProjectsRepository;
	
	@InjectMocks
	private UserProjectsServiceImpl toDoService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllUserProject(){
		List<UserProject> toDoList = new ArrayList<UserProject>();
		toDoList.add(new UserProject(1,"Todo Sample 1",true));
		toDoList.add(new UserProject(2,"Todo Sample 2",true));
		toDoList.add(new UserProject(3,"Todo Sample 3",false));
		when(userProjectsRepository.findAll()).thenReturn(toDoList);
		
		List<UserProject> result = toDoService.getAllUserProject();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetUserProjectById(){
		UserProject toDo = new UserProject(1,"Todo Sample 1",true);
		when(userProjectsRepository.findOne(1L)).thenReturn(toDo);
		UserProject result = toDoService.getUserProjectById(1);
		assertEquals(1, result.getId());
		assertEquals("Todo Sample 1", result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void saveUserProject(){
		UserProject toDo = new UserProject(8,"Todo Sample 8",true);
		when(userProjectsRepository.save(toDo)).thenReturn(toDo);
		UserProject result = toDoService.saveUserProject(toDo);
		assertEquals(8, result.getId());
		assertEquals("Todo Sample 8", result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void removeUserProject(){
		UserProject toDo = new UserProject(8,"Todo Sample 8",true);
		toDoService.removeUserProject(toDo);
        verify(userProjectsRepository, times(1)).delete(toDo);
	}
	
	

}

