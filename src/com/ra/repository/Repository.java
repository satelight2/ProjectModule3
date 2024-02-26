package com.ra.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll(Class<T> entityClass);
    List<T> findByPagination(Class<T> entityClass,int startPosition, int maxResult);
    T findId(Class<T> entityClass, Object... keys);
    T findByName(Class<T> entityClass, Object... keys);
    T findByEmpID(Class<T> entityClass, Object... keys);
    T findByUsername(Class<T> entityClass, Object... keys);
    T findByNameOrID(Class<T> entityClass, String any);
    T add(T entity);
    T edit(T entity);
    boolean remove(Class<T> entityClass, Object... keys);
}
