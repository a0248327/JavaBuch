package com.helloweenvsfei.forum.service.impl;

import java.util.List;

import com.helloweenvsfei.forum.bean.Person;
import com.helloweenvsfei.forum.service.IPersonService;
import com.helloweenvsfei.forum.struts.util.MD5Util;

public class PersonServiceImpl<T extends Person> extends ServiceImpl<T> implements IPersonService<T> {

	public T findPersonByAccount(String account) {	// 根据账号查询person
		List<T> person = this.getDao().createQuery(" select p from Person p " + " where lower(p.account) = lower(:account) and deleted = false ").setParameter("account", account.trim()).list();
		if (person.size() > 0)			// 如果有结果
			return person.get(0);		// 返回
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void create(T person) {	// 创建person
		if (findPersonByAccount(person.getAccount()) != null) {		// 查询账号实体是否存在
			throw new RuntimeException("帐号 " + person.getAccount() + " 已经存在。");
		}
		person.setPassword(MD5Util.calc(person.getPassword()));	// 密码加密
		this.getDao().create(person);								// 创建
	}

	public T getPerson(String account, String password) {	// 根据账号密码查询实体
		List<T> list = this.getDao().createQuery(" select p from Person p where p.account = :account " + " and p.password = :password and p.deleted = false ").setParameter("account", account)
				.setParameter("password", MD5Util.calc(password)).list();	// 密码加密
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
}
