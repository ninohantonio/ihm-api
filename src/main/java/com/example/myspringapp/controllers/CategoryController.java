package com.example.myspringapp.controllers;

import com.example.myspringapp.dto.CategoryDto;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    private Collection<Category> all(HttpSession session){
        return categoryService.all(session);
    }

    @GetMapping("/category/first")
    private Long firstcategory(HttpSession session){
        return categoryService.firstcategory(session);
    }

    @PostMapping( "/category/create")
    public ResponseEntity<?> create(@RequestBody CategoryDto category, HttpSession session){
        return categoryService.insert(category, session);
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return categoryService.delete(id);
    }
}
