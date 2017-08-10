package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ssm.dto.Person;
import com.ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/userLogin",method =RequestMethod.POST )
	public String userLogin(HttpServletRequest request, HttpServletResponse response){
		Person person = new Person();
		person.setName(request.getParameter("username"));
		person.setPassword(request.getParameter("password"));
		request.getSession().setAttribute("user",person);
		if(personService.selectByUser(person)==true){
			return "list";
		}else {
			return "redirect:/index.html";
		}
	};


	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(){
		List<Person> list =personService.getAllPerson();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",list);
		return map;
	}

	@RequestMapping(value="/listTwo",method=RequestMethod.POST)
	@ResponseBody
	public String listTwo(){
		List<Person> list =personService.getAllPerson();
		String json = JSON.toJSON(list).toString();
		System.out.print("sss"+json);
		return json;
	}


	@RequestMapping(value = "/logout" )
	public String userOut(HttpSession httpSession){
			httpSession.invalidate();
			return "list";
	};

	@RequestMapping(value = "/getMoneyDate",method = RequestMethod.POST)
	@ResponseBody
	public String getMoneyData(HttpServletRequest request){
		Person person = new Person();
		String date =request.getParameter("personName");
		String[] array = date.split(",");
		person.setName(array[0]);
		person.setToPerson(array[1]);
		person.setOutMoney(array[2]);
		person.setOwnMoney(array[2]);
		personService.updatePerInfo(person);
		return "list";
	};




	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Person record ){
		return "add";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Person record){
		int index =personService.insertPerson(record);
		System.out.println(index);
		return "list";
	}
	
	@RequestMapping("/edit")
	public String jump(Person person){
		return "update";
	}

	@RequestMapping("/sureName")
	public String sureLogin(Person person){
		return "welcom";
	}
}
