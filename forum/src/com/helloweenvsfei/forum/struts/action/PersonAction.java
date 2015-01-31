/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.helloweenvsfei.forum.struts.action;

import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helloweenvsfei.forum.bean.Person;
import com.helloweenvsfei.forum.service.IPersonService;
import com.helloweenvsfei.forum.struts.form.PersonForm;
import com.helloweenvsfei.forum.struts.util.PersonUtil;

/**
 * MyEclipse Struts Creation date: 07-03-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/person" name="personForm"
 *                input="/form/person/listPerson.jsp" scope="request"
 *                validate="true"
 */
public class PersonAction extends ForumAction {

	private IPersonService<Person> personService;

	/**
	 * 默认方法 返回到注册页面
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		// PersonForm personForm = (PersonForm) form;
		//
		// List<Person> personList = personService.list(" from Person ",
		// 0, 100,
		// null);
		//
		// request.setAttribute("personList", personList);
		//
		// return mapping.findForward("list");

		return this.initAdd(mapping, form, request, response);
	}

	/**
	 * 显示注册页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户注册");
		return mapping.findForward("add");	// 返回添加页面
	}

	/**
	 * 显示登录页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward initLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户登录");
		return new ActionForward("login", "/form/person/login.jsp", false);
		
	}

	/**
	 * 注册方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户注册");							// 设置标题

		Person person = personForm.getPerson();
		person.setIpCreated(request.getRemoteAddr());				// 记录注册id
		person.setIpLastActived(request.getRemoteAddr());			// 记录本次登陆ip
		person.setDateCreated(new Date());						// 记录注册时间
		person.setDateLastActived(new Date());					// 记录本次登陆时间

		if (person.getAccount() == null || person.getAccount().trim().length() == 0) {
			request.setAttribute("message", "请输入帐号");			// 如果没有输入账号
			return this.initAdd(mapping, form, request, response);	// 转到输入页面
		}

		if (person.getPassword() == null || person.getPassword().trim().length() == 0 || !person.getPassword().equals(personForm.getPassword())) {
			request.setAttribute("message", "密码不一致");			// 如果两次密码不一致
			return this.initAdd(mapping, form, request, response);	// 转到输入页面
		}

		try {
			personService.create(person);							// 保存到数据库
			PersonUtil.setPersonInf(request, response, person);		// 将信息写入session
			request.setAttribute("message", "注册成功");
			return new ActionForward("success", "/form/person/success.jsp", false);
		} catch (Exception e) {
			request.setAttribute("message", "注册失败，原因：" + e.getMessage());
			return this.initAdd(mapping, form, request, response);
		}
	}

	/**
	 * 登录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户登录");
		Person person = personService.getPerson(personForm.getPerson().getAccount(), personForm.getPerson().getPassword());

		if (person == null)
			throw new AccountException("用户名密码错误");

		PersonUtil.setPersonInf(request, response, person);
		person.setIpLastActived(request.getRemoteAddr());
		person.setDateLastActived(new Date());
		personService.save(person);
		request.setAttribute("message", "欢迎回来");
		return new ActionForward("success", "/form/person/success.jsp", false);
	}

	/**
	 * 注销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户注销");
		request.getSession(true).setAttribute(PersonUtil.PERSON_INFO, null);
		return new ActionForward("success", "/", true);
	}

	/**
	 * 查看用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("查看用户资料");
		Person person = personService.find(Person.class, personForm.getPerson().getId());
		request.setAttribute("person", person);
		return new ActionForward("view", "/form/person/viewPerson.jsp", false);
	}

	public IPersonService<Person> getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService<Person> personService) {
		this.personService = personService;
	}

}