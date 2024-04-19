package com.example.myspringapp.controllers;

import com.example.myspringapp.dto.AlimentDto;
import com.example.myspringapp.dto.AlimentationDto;
import com.example.myspringapp.entities.Alimentation;
import com.example.myspringapp.entities.Product;
import com.example.myspringapp.services.AlimentService;
import com.example.myspringapp.services.AlimentationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AlimentController {
    @Autowired
    private AlimentService alimentService;
    @Autowired
    private AlimentationService alimentationService;

    @GetMapping("/alimentation/{categoryid}")
    public Collection<Alimentation> all(@PathVariable("categoryid") Long id){
        return alimentationService.all(id);
    }

    @GetMapping("/aliment/bykeyword")
    public Collection<Product> getByKeyWord(@Param("keyword") String keyword, HttpSession session){
        return alimentService.getProductsByKeywordForCurrentUser(keyword, session);
    }

    @GetMapping("/aliment/search")
    public Collection<Product> getBySearch(@Param("search") String search, HttpSession session){
        return alimentService.getProductBySearchPromptForCurrentUser(search, session);
    }

    @GetMapping("/aliment")
    public Collection<Product> all(HttpSession session){
        return alimentService.all(session);
    }

    @PostMapping("/aliment/create")
    public ResponseEntity<?> addaliment(@RequestBody AlimentDto product, HttpSession session){
        return alimentService.insert(product, session);
    }

    @PostMapping("/alimentation/create")
    public ResponseEntity<?> create(@RequestBody AlimentationDto aliment, HttpSession session){
        return alimentationService.insert(aliment, session);
    }

    @PutMapping("/alimentation/update/{id}")
    public ResponseEntity<?> update(@RequestBody AlimentationDto aliment, @PathVariable("id") Long id){
        return alimentationService.update(aliment, id);
    }

    @PutMapping("/aliment/update/{id}")
    public ResponseEntity<?> modifyaliment(@RequestBody AlimentDto product, @PathVariable("id") Long id){
        return alimentService.update(product, id);
    }

    @PutMapping("/aliment/substract/{id}")
    public void substract(@RequestBody Double quantite, @PathVariable("id") Long id){
        alimentService.substract(quantite, id);
    }

    @DeleteMapping("/alimentation/delete/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return alimentationService.delete(id);
    }

    @DeleteMapping("/aliment/delete/{id}")
    public boolean deletealiment(@PathVariable("id") Long id){
        return alimentService.delete(id);
    }

}
