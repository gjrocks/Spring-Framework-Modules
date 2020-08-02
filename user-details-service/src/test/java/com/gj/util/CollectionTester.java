package com.gj.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CollectionTester {

	@Test
	public void setTester(){
		
	Set<String> st=new HashSet<>();	
	
	
	assertTrue(st.add(null));
	assertFalse(st.add(null));
	
	
	
	
	}
}
