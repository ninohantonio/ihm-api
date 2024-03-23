package com.example.myspringapp.services;

import com.example.myspringapp.dao.ProductRepository;
import com.example.myspringapp.entities.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlimentService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> insert(Product produit, HttpSession session){
        try{
            Product a = new Product();
            a.setQuantite(produit.getQuantite());
            a.setDesignation(produit.getDesignation());
            a.setUser(userService.connecteduser(session));
            productRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> update(Product product, Long id){
        try{
            Product a = productRepository.findById(id).get();
            a.setDesignation(product.getDesignation());
            a.setQuantite(product.getQuantite());
            productRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public void substract(Double quantite, Long id){
        Product a = productRepository.findById(id).get();
        a.setQuantite(a.getQuantite() - quantite);
        productRepository.save(a);
    }

    public boolean delete(Long id){
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
