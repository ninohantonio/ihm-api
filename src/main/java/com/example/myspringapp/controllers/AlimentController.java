package com.example.myspringapp.controllers;

import com.example.myspringapp.entities.Alimentation;
import com.example.myspringapp.entities.Product;
import com.example.myspringapp.services.AlimentService;
import com.example.myspringapp.services.AlimentationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlimentController {
    @Autowired
    private AlimentService alimentService;
    @Autowired
    private AlimentationService alimentationService;

    @PostMapping("/aliment/create")
    public ResponseEntity<?> addaliment(@RequestBody Product product, HttpSession session){
        return alimentService.insert(product, session);
    }

    @PostMapping("/alimentation/create")
    public ResponseEntity<?> create(@RequestBody Alimentation aliment, HttpSession session){
        return alimentationService.insert(aliment, session);
    }

    @PutMapping("/alimentation/update/{id}")
    public ResponseEntity<?> update(@RequestBody Alimentation aliment, @PathVariable("id") Long id){
        return alimentationService.update(aliment, id);
    }

    @PutMapping("/aliment/update/{id}")
    public ResponseEntity<?> modifyaliment(@RequestBody Product product, @PathVariable("id") Long id){
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
