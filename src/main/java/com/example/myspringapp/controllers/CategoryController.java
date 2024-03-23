package com.example.myspringapp.controllers;

import com.example.myspringapp.entities.Category;
import com.example.myspringapp.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/category/create")
    public ResponseEntity<?> insert(@RequestBody Category category, HttpSession session){
        return categoryService.insert(category, session);
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return categoryService.delete(id);
    }
}
