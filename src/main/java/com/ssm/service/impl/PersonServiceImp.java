package com.ssm.service.impl;

import com.ssm.dto.Person;
import com.ssm.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.ssm.service.PersonService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("personService")
@Transactional
public class PersonServiceImp implements PersonService{
	@Autowired
	private PersonMapper personDao;

	//登录
	@Override
	public boolean selectByUser(Person person) {
		boolean index = false ;
		String userName = person.getName();
		System.out.println("username"+userName);
		if(userName != null){
			int taget = personDao.selectByUserName(person);
			if (taget > 0){
				int passtage = personDao.selectPasswordTrue(person);
				if (passtage > 0){
					index = true;
				}else {
					System.out.println("密码错误");
					index = false;
				}
			}
			System.out.println("没有记录");
		}
		return index;
	}

	@Override
	public List<Person> getAllPerson() {
		return this.personDao.selectAllInfo();
	}

	@Override
	public int insertPerson(Person record) {
		int index = personDao.insert(record);
		return index;
	}

	@Override
	public Person userLogin(Person name) {
		return null;
	}

	//转出的方法
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updatePerInfo(Person person) {
		int target = personDao.updateByPrimaryKeySelective(person);
		int i = 1/0;
		updateInPerInfo(person);
		return target;

	}
	//转进的方法
	@Override
	public int updateInPerInfo(Person person) {
		person.setOwnMoney(person.getOutMoney());
		person.setEarnMoney(person.getOutMoney());
		person.setFromPerson(person.getName());
		person.setName(person.getToPerson());
		person.setToPerson("");
		person.setOutMoney("");
		return personDao.updateByPrimaryKeyToPerson(person);
	}

	@Override
	public int updataToPerInfo(Person person) {
		return 0;
	}


}
