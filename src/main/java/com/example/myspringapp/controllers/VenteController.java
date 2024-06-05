package com.example.myspringapp.controllers;

import com.example.myspringapp.dto.VenteDto;
import com.example.myspringapp.entities.Vente;
import com.example.myspringapp.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class VenteController {
    @Autowired
    private VenteService venteService;

    @GetMapping("/vente/{category}")
    public Collection<Vente> index(@PathVariable("category") Long id){
        return venteService.allVente(id);
    }

    @PostMapping("/vente/create")
    public ResponseEntity<?> create(@RequestBody VenteDto venteDto){
        return venteService.createVente(venteDto);
    }
}
