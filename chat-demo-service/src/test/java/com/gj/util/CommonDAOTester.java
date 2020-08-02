package com.gj.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.gj.exception.RecordDuplicateException;
import com.gj.repository.ICommonDao;

public class CommonDAOTester {

	
	@Autowired
	@Mock
	ICommonDao<Person> icommonDao;
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		Person p=new Person(1);
		BDDMockito.given(icommonDao.delete(1L)).willAnswer(answer->{
			
			return p;
		}
		);
		
		
	}
	
	@Test
	public void testSave(){
		Person p=new Person();
		try{
		Mockito.when(icommonDao.save(p)).thenReturn(p);
		assertNotNull(icommonDao.save(p));
		}catch(Exception e){
			
		}
	}
	
	
	@Test(expected=RecordDuplicateException.class)
	public void testSave2() throws Exception{
		Person p=new Person();
		Mockito.when(icommonDao.save(p)).thenThrow(RecordDuplicateException.class);
		icommonDao.save(p);
		
	}
	
	@Test
	public void testFindById() throws Exception{
	
		Mockito.when(icommonDao.findById(1L)).thenReturn(new Person(1L));
		assertEquals(1L, icommonDao.findById(1L).getId());
	}
	
	@Test
	public void testdelete() throws Exception{
	
		
		assertEquals(1L, icommonDao.delete(1L).getId());
	}
}
