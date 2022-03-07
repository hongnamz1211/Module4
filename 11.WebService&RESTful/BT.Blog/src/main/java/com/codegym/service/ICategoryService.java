package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category blog);

    void deleteById(long id);
}
