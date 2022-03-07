package com.codegym.service;

import com.codegym.model.Blog;

import java.util.Optional;

public interface IBlogService {
    Iterable<Blog> findAll();

    Optional<Blog> findById(long id);

    Blog save(Blog blog);

    void deleteById(long id);

    Iterable<Blog> findByName(String name);

    Iterable<Blog> findByCategory(long id);
}
