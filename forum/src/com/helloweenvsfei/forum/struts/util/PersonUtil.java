package com.helloweenvsfei.forum.struts.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloweenvsfei.forum.bean.Person;

public class PersonUtil {

	public static final String PERSON_INFO = "personInfo";

	public static PersonInfo getPersonInfo(HttpServletRequest request, HttpServletResponse response) {

		return (PersonInfo) request.getSession(true).getAttribute(PERSON_INFO);		// 3. 得到信息
	}

	public static void setPersonInf(HttpServletRequest request, HttpServletResponse response, PersonInfo personInfo) {

		request.getSession(true).setAttribute(PERSON_INFO, personInfo);				// 2. 设置信息
	}

	public static void setPersonInf(HttpServletRequest request, HttpServletResponse response, Person person) {	// 1. 设置用户信息

		PersonInfo personInfo = new PersonInfo();
		personInfo.setId(person.getId());
		personInfo.setAccount(person.getAccount());
		setPersonInf(request, response, personInfo);
	}
}
