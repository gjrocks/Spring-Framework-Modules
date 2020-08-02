package com.gj.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="PersonDeptList")
public class PersonDeptList {

	
private List<PersonDeptLink> personList;

	
	public PersonDeptList(){
		super();
		personList=new ArrayList<PersonDeptLink>();
	}
	public PersonDeptList(List<PersonDeptLink> personList) {
		super();
		this.personList = personList;
	}

	public List<PersonDeptLink> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonDeptLink> personList) {
		this.personList = personList;
	}
	
	
	
}
