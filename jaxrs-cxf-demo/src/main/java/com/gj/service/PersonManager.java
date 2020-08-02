package com.gj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gj.dao.IPersonDAO;
import com.gj.exception.DuplicatePersonException;
import com.gj.model.Person;
import com.gj.model.PersonDeptList;
@Service
public class PersonManager implements IPersonManager {
    
	@Autowired(required=true)
	IPersonDAO personDao;

	@Override
	public Person addPerson(Person p) throws DuplicatePersonException{
			getPersonDao().addPerson(p);
			System.out.println("Person added");
		
		return p;
	}

	@Override
	public Person removePerson(int id) {
		Person p = null;
		try {
			p = getPersonDao().deletePerson(id);
			System.out.println("Person removed");
		} catch (Exception e) {

		}
		return p;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> listPersons = null;
		try {
			listPersons = getPersonDao().getAllPersons();
			System.out.println("All Persons retrived");
		} catch (Exception e) {

		}
		return listPersons;
	}

	@Override
	public Person getPersonByID(int id) {

		Person p = null;
		try {
			p = getPersonDao().getPerson(id);
			if (p != null)
				System.out.println("Person retrived");
			else
				System.out.println("Person does not exist");
		} catch (Exception e) {

		}
		return p;
	}

	public IPersonDAO getPersonDao() {
		return personDao;
	}

	public void setPersonDao(IPersonDAO personDao) {
		this.personDao = personDao;
	}

	@Override
	public PersonDeptList getPersonDeptList() {
		
		return this.getPersonDao().getPersonDeptList();
	}

}
