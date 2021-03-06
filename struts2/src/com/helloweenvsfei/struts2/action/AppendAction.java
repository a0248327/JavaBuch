package com.helloweenvsfei.struts2.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;

import com.opensymphony.xwork2.Action;

@Results(value = { @Result(name = "success", value = "/appendTag.jsp") })
@SuppressWarnings("all")
public class AppendAction {

	private List<String> maleList = new ArrayList<String>() {
		{
			add("Machael");
			add("Scorfield");
		}
	};

	private List<String> femaleList = new ArrayList<String>() {
		{
			add("Janice");
			add("Marry");
		}
	};

	public String execute() {

		return Action.SUCCESS;
	}

	public List<String> getMaleList() {
		return maleList;
	}

	public void setMaleList(List<String> maleList) {
		this.maleList = maleList;
	}

	public List<String> getFemaleList() {
		return femaleList;
	}

	public void setFemaleList(List<String> femaleList) {
		this.femaleList = femaleList;
	}

}
