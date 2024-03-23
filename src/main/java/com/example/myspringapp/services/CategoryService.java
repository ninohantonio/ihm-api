package com.example.myspringapp.services;

import com.example.myspringapp.dao.CategoryRepository;
import com.example.myspringapp.dao.UserRepository;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public Collection<Category> all(HttpSession session){
        User connecteduser = userService.connecteduser(session);
        User user = userRepository.findById(connecteduser.getId()).get();
        return user.getCategories();
    }

    public ResponseEntity<?> insert(Category category, HttpSession session){
        try{
            Category a = categoryRepository.save(category);
            a.setUser(userService.connecteduser(session));
            Category created = categoryRepository.save(a);
            return new ResponseEntity<>(created, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> delete(Long id){
        try{
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("deleted");
        }catch(Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }
}
