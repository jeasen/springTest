package com.ssm.service;


import com.ssm.dto.Person;

import java.util.List;

public interface PersonService {
	//登录验证的方法
	public boolean selectByUser(Person person);

	//获取全部信息的方法
	public List<Person> getAllPerson();
	
	public int insertPerson(Person record);
	
	public Person userLogin(Person name);

	//转出的方法
	public int updatePerInfo(Person person);

	//转进来的方法
	public int updateInPerInfo(Person person);




	public int updataToPerInfo(Person person);
}
