package com.codegym.repository;

import com.codegym.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface IBlogRepository extends CrudRepository<Blog, Long> {
    Iterable<Blog> findBlogsByNameContaining(String name);

    Iterable<Blog> findBlogsByCategory_Id(long id);
}
