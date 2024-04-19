package com.example.myspringapp.services;

import com.example.myspringapp.dao.ProductRepository;
import com.example.myspringapp.dao.UserRepository;
import com.example.myspringapp.dto.AlimentDto;
import com.example.myspringapp.entities.Product;
import com.example.myspringapp.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlimentService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public Collection<Product> all(HttpSession session){
        User connecteduseruser = userService.connecteduser(session);
        User user = userRepository.findById(connecteduseruser.getId()).get();
        return user.getProducts();
    }

    public Collection<Product> getProductsByKeywordForCurrentUser(String keyword, HttpSession session) {
        User connecteduser = userService.connecteduser(session);
        return productRepository.findByUserAndDesignationContaining(connecteduser, keyword);
    }

    public Collection<Product> getProductBySearchPromptForCurrentUser(String searchTerm, HttpSession session){
        User user = userService.connecteduser(session);
        try {
            // Essayer de convertir searchTerm en double
            double quantite = Double.parseDouble(searchTerm);
            // Si la conversion réussit, rechercher par quantité
            return productRepository.findByUserAndQuantite(user, quantite);
        } catch (NumberFormatException e) {
            // Si la conversion échoue, rechercher par désignation
            return productRepository.findByUserAndDesignationContainingIgnoreCase(user, searchTerm);
        }
    }

    public ResponseEntity<?> insert(AlimentDto produit, HttpSession session){
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

    public ResponseEntity<?> update(AlimentDto product, Long id){
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
