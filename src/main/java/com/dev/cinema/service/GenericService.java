package com.dev.cinema.service;

public interface GenericService<T> {

    T add(T element);

    T findById(Long id);
}
