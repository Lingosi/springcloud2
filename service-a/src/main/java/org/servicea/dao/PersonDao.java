package org.servicea.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.servicea.model.Person;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;



@Mapper
public interface PersonDao extends BaseMapper<Person>{
	String getName(@Param("id") Integer id);
	
	List<Person> findAllPerson(Page<Person> page);
	
	Person getPerson(Page<Person> page, @Param("per") Person person);
	
	List<Person> findPersonBy(Page<Person> page, @Param("per") Person person);
	
	Integer countPersonBy(Page<Person> page, @Param("per") Person person);
	
	Integer getMaxId();
	
	void submitItem(List<Person> persons);
}
