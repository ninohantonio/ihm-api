package com.example.myspringapp.controllers;

import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.services.AnimalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/animal/getphoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo(@PathVariable("id") Long id) throws Exception {
        return animalService.getphoto(id);
    }

    @PostMapping("/animal/create")
    public ResponseEntity<?> insert(@RequestBody Animal animal, HttpSession session){
        return animalService.insert(animal, session);
    }

    @PostMapping(value = "/animal/setphoto/{id}")
    public void setphoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws Exception {
        animalService.insertphoto(file, id);
    }

    @PutMapping("/animal/update/{id}")
    public ResponseEntity<?> update(@RequestBody Animal animal, @PathVariable("id") Long id){
        return animalService.update(animal, id);
    }

    @DeleteMapping("/animal/delete/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return animalService.delete(id);
    }

}
