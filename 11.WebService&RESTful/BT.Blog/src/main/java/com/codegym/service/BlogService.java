package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteById(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Iterable<Blog> findByName(String name) {
        return blogRepository.findBlogsByNameContaining(name);
    }

    @Override
    public Iterable<Blog> findByCategory(long id) {
        return blogRepository.findBlogsByCategory_Id(id);
    }
}
