package com.helloweenvsfei.forum.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity																// 实体类配置
@Table																// 表格配置
public class Person extends BaseBean {

	private String account;

	private String password;

	private String sex;

	private String name;

	private String birthday;

	private String email;

	private String ipCreated;

	@Temporal(value = TemporalType.TIMESTAMP)					// 最后一次登陆的时间
	private Date dateLastActived;

	private String ipLastActived;										// 最后一次登陆的ip

	@ManyToMany(mappedBy = "administrators")						// 多对多属性
	private Set<Board> boardsAdministrated = new HashSet<Board>();	// 管理的版面

	public String getAccount() {
		return account;
	}

	public Date getDateLastActived() {
		return dateLastActived;
	}

	public void setDateLastActived(Date dateLastActive) {
		this.dateLastActived = dateLastActive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIpCreated() {
		return ipCreated;
	}

	public void setIpCreated(String ipCreated) {
		this.ipCreated = ipCreated;
	}

	public String getIpLastActived() {
		return ipLastActived;
	}

	public void setIpLastActived(String ipLastActived) {
		this.ipLastActived = ipLastActived;
	}

	public Set<Board> getBoardsAdministrated() {
		return boardsAdministrated;
	}

	public void setBoardsAdministrated(Set<Board> boardsAdministrated) {
		this.boardsAdministrated = boardsAdministrated;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
