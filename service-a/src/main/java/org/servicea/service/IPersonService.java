package org.servicea.service;



import java.util.List;

import org.servicea.model.Person;

import com.baomidou.mybatisplus.plugins.Page;


public interface IPersonService {
	String getName(Integer id);
	
	Page<Person> getAllPerson(Page<Person> page);
	
	Person getPerson(Page<Person> page, Person per);
	
	Page<Person> getAllPerson2(Page<Person> page);
	
	Page<Person> getPersons(Page<Person> page, Person per);
	
	Page<Person> getAllPerson3(Page<Person> page, Person per);
	
	Integer getMaxId();
	
	void insertPersons(List<Person> persons);
}
