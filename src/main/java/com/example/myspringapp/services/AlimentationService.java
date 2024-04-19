package com.example.myspringapp.services;

import com.example.myspringapp.dao.AlimentationRepository;
import com.example.myspringapp.dao.CategoryRepository;
import com.example.myspringapp.dao.ProductRepository;
import com.example.myspringapp.dto.AlimentationDto;
import com.example.myspringapp.entities.Alimentation;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlimentationService {
    @Autowired
    private AlimentationRepository alimentationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public Collection<Alimentation> all(Long categoryId){
        Category category = categoryRepository.findById(categoryId).get();
        return category.getAlimentations();
    }

    public ResponseEntity<?> insert(AlimentationDto aliment, HttpSession session){
        try{
            Alimentation a = new Alimentation();
            Product product = productRepository.findById(aliment.getProduct()).get();
            Category category = categoryRepository.findById(aliment.getCategory()).get();
            a.setQuantite(aliment.getQuantite());
            a.setHeure(aliment.getHeure());
            a.setProduct(product);
            a.setAgeMin(aliment.getAgeMin());
            a.setAgeMax(aliment.getAgeMax());
            a.setCategory(category);
            a.setUser(userService.connecteduser(session));
            alimentationRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> update(AlimentationDto aliment, Long id){
        try{
            Alimentation a = alimentationRepository.findById(id).get();
            Product product = productRepository.findById(aliment.getProduct()).get();
            a.setQuantite(aliment.getQuantite());
            a.setHeure(aliment.getHeure());
            a.setProduct(product);
            a.setAgeMin(aliment.getAgeMin());
            a.setAgeMax(aliment.getAgeMax());
            Alimentation save = alimentationRepository.save(a);
            return new ResponseEntity<>(save, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public boolean delete(Long id){
        try{
            alimentationRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
