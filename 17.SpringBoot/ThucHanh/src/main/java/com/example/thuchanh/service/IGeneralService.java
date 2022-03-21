package com.example.thuchanh.service;

import com.example.thuchanh.model.Customer;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findByOne(long id);

    T save(T t);

    void deleteById(long id);
}
