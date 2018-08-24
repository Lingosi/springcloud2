package org.servicea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.servicea.dao.PersonDao;
import org.servicea.model.Person;
import org.servicea.service.IPersonService;
import org.servicea.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;




@Service
public class PersonServiceImpl implements IPersonService{

	@Autowired
	PersonDao personDao;
	public String getName(Integer id) {
		return personDao.getName(id);
	}
	
	
	public Page<Person> getAllPerson(Page<Person> page){
		page.setRecords(personDao.findAllPerson(page));
		return page;
	}
	
	public Page<Person> getAllPerson2(Page<Person> page){
		List<Person> persons = personDao.findAllPerson(page);
		//return new MyPage<Person>(page.getCurrent(), page.getPages(), 10, persons);
		page.setRecords(persons);
		return page;
	}
	
	public Person getPerson(Page<Person> page, Person per){
		Person person = personDao.getPerson(page, per);
		return person;
	}
	
	public Page<Person> getAllPerson3(Page<Person> page, Person per){
		List<Person> persons = personDao.findPersonBy(page, per);
		page.setRecords(persons);
		return page;
	}
	
	public Page<Person> getPersons(Page<Person> page, Person per){
		Integer count = personDao.countPersonBy(page, per);
		System.out.println("总数：" + count + "，每页：" + page.getPages());
		if(count <= 0){
			page.setRecords(new ArrayList<Person>());
			//return new MyPage<Person>(page.getCurrent(), page.getPages(), 0, new ArrayList<Person>());
			return page;
		}
		
		List<Person> persons = personDao.findPersonBy(page, per);
		System.out.println("当前页：" + page.getCurrent() + "，每页：" + page.getPages() + "，数据有：" + persons.size());
		//return new MyPage<Person>(page.getCurrent(), page.getPages(), count, persons);
		page.setRecords(persons);
		return page;
	}
	
	public Integer getMaxId() {
		
		return personDao.getMaxId();
	}
	
	public void insertPersons(List<Person> persons) {
		personDao.submitItem(persons);
		
	}
	
	
	
}
