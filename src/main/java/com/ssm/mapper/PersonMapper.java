package com.ssm.mapper;


import com.ssm.dto.Person;

import java.util.List;

public interface PersonMapper {
    //验证名字是否正确
    int selectByUserName(Person record);
    //验证用户名与密码是否正确
    int selectPasswordTrue(Person person);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKeyToPerson(Person person);

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);



    int updateByPrimaryKey(Person record);
   //获得所有信息
    public List<Person> selectAllInfo();

}