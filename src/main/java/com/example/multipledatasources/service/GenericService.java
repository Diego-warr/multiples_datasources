package com.example.multipledatasources.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Optional<T> findById(Integer id);

    T save( T t);

    T update(T t);

    int delete(T t);

    int deleteAll(List<T> list);

}
