package com.dev.cinema.dao;

public interface GenericDao<T> {

    T add(T element);

    T getById(Long id);
}
