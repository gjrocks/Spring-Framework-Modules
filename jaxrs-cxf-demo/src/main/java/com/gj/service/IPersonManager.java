package com.gj.service;

import java.util.List;

import com.gj.exception.DuplicatePersonException;
import com.gj.model.Person;
import com.gj.model.PersonDeptList;

public interface IPersonManager {

	public Person addPerson(Person p)throws DuplicatePersonException;
	public Person  removePerson(int id);
	public List<Person> getAllPersons();
	public Person getPersonByID(int id);
	public PersonDeptList getPersonDeptList();
	
}
