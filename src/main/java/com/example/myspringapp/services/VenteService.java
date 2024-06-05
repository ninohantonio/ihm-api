package com.example.myspringapp.services;

import com.example.myspringapp.dao.AnimalRepository;
import com.example.myspringapp.dao.CategoryRepository;
import com.example.myspringapp.dao.VenteRepository;
import com.example.myspringapp.dto.VenteDto;
import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.Vente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VenteService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private AnimalService animalService;

    public Collection<Vente> allVente(Long category){
        Category category1 = categoryRepository.findById(category).get();
        return venteRepository.findByCategory(category1);
    }

    public ResponseEntity<?> createVente(VenteDto venteDto){
        try{
            Vente vente = new Vente();
            Category category = categoryRepository.findById(venteDto.getCategory()).get();
            Animal animal = animalRepository.findById(venteDto.getAnimal()).get();
            vente.setId(animal.getId());
            vente.setDescription(venteDto.getDescription());
            vente.setCategory(category);
            vente.setPoids(animal.getPoids());
            Vente newvente = venteRepository.save(vente);
            animalService.delete(animal.getId());
            return new ResponseEntity<>(newvente, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }
}
