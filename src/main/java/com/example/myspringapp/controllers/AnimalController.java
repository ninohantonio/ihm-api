package com.example.myspringapp.controllers;

import com.example.myspringapp.dto.AnimalDto;
import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.services.AnimalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@RestController
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animal/category/{id}")
    public Collection<Animal> parcategorie(@PathVariable("id") Long id){
        return animalService.parcategorie(id);
    }

    @GetMapping("/animal/sexe/{sexeid}/{category}")
    public Collection<Animal> parsexe(@PathVariable("sexeid") Long id, @PathVariable("category") Long cat){
        return animalService.parsexe(id, cat);
    }

    @GetMapping("/animal/filterbyhealth/{category}")
    public Collection<Animal> filterbyhealth(@PathVariable("category") Long categoryId,@Param("sante") String sante){
        return animalService.filterBySante(sante, categoryId);
    }

    @GetMapping("/animal/filterbyvaccine/{category}")
    public Collection<Animal> filterbyvaccin(@PathVariable("category") Long categoryId, @Param("vaccine") Boolean vaccine){
        return animalService.filterByVaccine(categoryId, vaccine);
    }

    @GetMapping("/animal/filterbypoid/{category}")
    public Collection<Animal> filterbypoid(@PathVariable("category") Long categoryId){
        return animalService.filterByPoid(categoryId);
    }

    @GetMapping("/animal/filterbyage/{category}")
    public Collection<Animal> filterbyage(@PathVariable("category") Long categoryId){
        return animalService.filterByAge(categoryId);
    }

    @GetMapping(value = "/animal/getphoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo(@PathVariable("id") Long id) throws Exception {
        return animalService.getphoto(id);
    }

    @PostMapping("/animal/create")
    public ResponseEntity<?> insert(@RequestBody AnimalDto animal, HttpSession session){
        return animalService.insert(animal, session);
    }

    @PostMapping("/animal/setphoto/{id}")
    public void setphoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws Exception {
        animalService.insertphoto(file, id);
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<?> specific(@PathVariable("id") Long id){
        return animalService.specific(id);
    }

    @PutMapping("/animal/update/{id}")
    public ResponseEntity<?> update(@RequestBody AnimalDto animal, @PathVariable("id") Long id){
        return animalService.update(animal, id);
    }

    @DeleteMapping("/animal/delete/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return animalService.delete(id);
    }

}
