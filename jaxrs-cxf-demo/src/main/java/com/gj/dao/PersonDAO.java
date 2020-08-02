package com.gj.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



import org.springframework.stereotype.Component;

import com.gj.exception.DuplicatePersonException;
import com.gj.model.Person;
import com.gj.model.PersonDeptLink;
import com.gj.model.PersonDeptList;
@Component
public class PersonDAO implements IPersonDAO {

	private static Map<Integer, Person> personDB = new ConcurrentHashMap<Integer, Person>();
	static {
		personDB.put(1, new Person(1, 50, "CEO", "CEO_surname", new Date(),
				100000));
	}

	@Override
	public Person getPerson(int id) {

		return personDB.get(id);
	}

	@Override
	public void addPerson(Person p) throws DuplicatePersonException{
		if(personDB.get(p.getId())!=null)
			throw new DuplicatePersonException("Person ID " + p.getId()+ " Already there");
		personDB.put(p.getId(), p);

	}

	@Override
	public List<Person> getAllPersons() {

		return new java.util.ArrayList<Person>(personDB.values());
	}

	@Override
	public Person deletePerson(int id) {

		return personDB.remove(id);
	}

	@Override
	public PersonDeptList getPersonDeptList() {
		
	List<Person> li=new java.util.ArrayList<Person>(personDB.values());
	List<PersonDeptLink> list=new ArrayList<PersonDeptLink>();
	for (Person person : li) {
		PersonDeptLink link=new PersonDeptLink(person.getDeptCode(),person.getId());
		list.add(link);
	}
		return new PersonDeptList(list);
	}

}
