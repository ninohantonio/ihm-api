package com.example.myspringapp.controllers;

import com.example.myspringapp.entities.Vaccination;
import com.example.myspringapp.services.VaccinationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    @PostMapping("/vaccination/create")
    public ResponseEntity<?> create(@RequestBody Vaccination vaccination, HttpSession session){
        return vaccinationService.insert(vaccination, session);
    }

    @PutMapping("/vaccination/update/{id}")
    public ResponseEntity<?> update(@RequestBody Vaccination vaccination, @PathVariable("id") Long id){
        return vaccinationService.update(vaccination, id);
    }

    @PutMapping("/vaccination/docheck/{id}")
    public void docheck(@PathVariable("id") Long id){
        vaccinationService.docheck(id);
    }

    @DeleteMapping("vaccination/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        return vaccinationService.delete(id);
    }
}
