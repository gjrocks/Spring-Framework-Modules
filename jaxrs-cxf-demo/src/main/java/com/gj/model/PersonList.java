package com.gj.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="PersonList")
public class PersonList {

	
	private List<Person> personList;

	
	public PersonList(){
		super();
		personList=new ArrayList<Person>();
	}
	public PersonList(List<Person> personList) {
		super();
		this.personList = personList;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	
	
}
