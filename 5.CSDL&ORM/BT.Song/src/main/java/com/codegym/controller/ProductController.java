package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Product> products = (ArrayList<Product>) iProductService.findAll();
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products!");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = iProductService.remove(id);
        if (product == null) {
            modelAndView.addObject("message", "Id invalid!");
            modelAndView.addObject("color", "red");
        }
        ArrayList<Product> products = (ArrayList<Product>) iProductService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = iProductService.findById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @GetMapping("/search/{search}")
    public ModelAndView search(@PathVariable("search") String name) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = iProductService.findByName(name);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute ProductForm product) {
        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product1 = new Product(product.getId(), product.getName(), product.getPrice(), product.getDescription(), fileName);
        iProductService.save(product1);
        ModelAndView modelAndView = new ModelAndView("create");
        if (product1 != null) {
            modelAndView.addObject("message", "Create successfully!");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = iProductService.findById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute ProductForm product, @PathVariable int id) {
        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("edit");

        Product product1 = new Product(id, product.getName(), product.getPrice(), product.getDescription(), fileName);
        iProductService.save(product1);
        if (product1 != null) {
            modelAndView.addObject("message", "Update successfully");
        }
        return modelAndView;
    }
}
