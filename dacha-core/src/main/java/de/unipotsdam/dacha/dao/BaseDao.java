package de.unipotsdam.dacha.dao;

public interface BaseDao<T> {

	T findById(long id);

	void saveOrUpdate(T t);
}
