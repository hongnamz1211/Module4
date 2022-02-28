package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(int id);

    Product findByName(String name);

    Product save(Product product);

    Product remove(int id);
}
