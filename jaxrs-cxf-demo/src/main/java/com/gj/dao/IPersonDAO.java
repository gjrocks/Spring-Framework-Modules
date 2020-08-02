package com.gj.dao;

import java.util.List;

import com.gj.exception.DuplicatePersonException;
import com.gj.model.Person;
import com.gj.model.PersonDeptList;

public interface IPersonDAO {

	public Person getPerson(int id);
	public void addPerson(Person p)throws DuplicatePersonException;
	public  List<Person> getAllPersons();
	public Person deletePerson(int id);
	public PersonDeptList getPersonDeptList();
}
