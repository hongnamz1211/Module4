package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogs")
public class WebServiceController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        Iterable<Blog> blogs = blogService.findAll();
        if (!blogs.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Blog>> findByName(@RequestParam("search") String search) {
        Iterable<Blog> blogs = blogService.findByName(search);
        if (!blogs.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createProduct(@RequestBody Blog blog) {
        Blog blogCreate = blogService.save(blog);
        return new ResponseEntity<>(blogCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlog(@RequestBody Blog blog, @PathVariable long id) {
        Optional<Blog> blogEdit = blogService.findById(id);
        if (!blogEdit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogEdit.get().getId());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.deleteById(id);
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Iterable<Blog>> findByCategory(@PathVariable long id) {
        Iterable<Blog> blogs = blogService.findByCategory(id);
        if (!blogs.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> findAllCategory() {
        Iterable<Category> categories = categoryService.findAll();
        if (!categories.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
