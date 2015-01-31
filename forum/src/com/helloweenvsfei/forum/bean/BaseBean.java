package com.helloweenvsfei.forum.bean;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass										// 实体类父亲
public class BaseBean {

	@Id													// id配置
	@GeneratedValue(strategy = GenerationType.AUTO)	// id规则配置
	private Integer id;

	@Version											// 版本列
	private Integer version;								// hibernate自动维护该列

	private boolean deleted;

	@Temporal(value = TemporalType.TIMESTAMP)		// 时间戳
	private Date dateCreated;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
