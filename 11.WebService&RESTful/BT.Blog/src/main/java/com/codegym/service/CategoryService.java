package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category blog) {
        return categoryRepository.save(blog);
    }

    @Override
    public void deleteById(long id) {
        categoryRepository.deleteById(id);
    }
}
