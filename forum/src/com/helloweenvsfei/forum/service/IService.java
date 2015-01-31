package com.helloweenvsfei.forum.service;

import java.util.List;

public interface IService<T> {				// service接口，基本方法

	public T find(Class<T> clazz, int id);		// 根据id查找实体

	public void create(T baseBean);			// 创建实体

	public void save(T baseBean);			// 保存实体

	public void delete(T baseBean);			// 删除实体

	public List<T> list(String hql);			// 查询所以实体

	public int getTotalCount(String hql, Object... params);		// 查询页数

	public List<T> list(String hql, int firstResult, int maxSize, Object... params);		// 分页实体
}
