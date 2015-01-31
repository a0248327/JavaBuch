package com.helloweenvsfei.forum.struts.form;

import org.apache.struts.action.ActionForm;

public class ForumForm extends ActionForm {	// FormBean 基类

	private String action;						// action 参数

	private String title;							// 页面标题，jsp显示该属性

	public String getAction() {					// action属性的getter方法
		return action;
	}

	public void setAction(String action) {		// action属性的setter方法
		this.action = action;
	}

	public String getTitle() {						// title属性的getter方法
		return title;
	}

	public void setTitle(String title) {			// title属性的setter方法
		this.title = title;
	}
}
