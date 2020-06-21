package com.dev.cinema.dao;

public interface GenericDao<T> {

    T add(T element);

    T findById(Long id);
}
