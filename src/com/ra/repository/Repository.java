package com.ra.repository;

import java.lang.annotation.Annotation;
import java.util.List;

public interface Repository<T> {
    List<T> findAll(Class<T> entityClass);
    List<T> findByPagination(Class<T> entityClass,int startPosition, int maxResult);
    T findWithAnything(Class<T> entityClass,Class<? extends Annotation> annotationClass1,Class<? extends Annotation> annotationClass2, Object... keys);
    T add(T entity);
    T edit(T entity);
    boolean remove(Class<T> entityClass, Object... keys);
}
