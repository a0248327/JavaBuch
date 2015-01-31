package com.helloweenvsfei.forum.dao;

import java.util.List;

import org.hibernate.Query;

public interface IDao<T> {									// DAO 接口，使用范型定义，可操作所以实体

	public T find(Class<T> clazz, int id);						// 根据 id 查找实体

	public void create(T baseBean);							// 创建实体

	public void save(T baseBean);							// 保存实体	

	public void delete(T baseBean);							// 删除实体

	public List<T> list(String hql);							// 查询实体

	public int getTotalCount(String hql, Object... params);		// 查询总是	

	public List<T> list(String hql, int firstResult, int maxSize, Object... params);		// 查询某页实体

	public Query createQuery(String hql);					// 创建query对象
}
