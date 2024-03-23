package com.example.myspringapp.services;

import com.example.myspringapp.dao.AlimentationRepository;
import com.example.myspringapp.entities.Alimentation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlimentationService {
    @Autowired
    private AlimentationRepository alimentationRepository;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> insert(Alimentation aliment, HttpSession session){
        try{
            Alimentation a = new Alimentation();
            a.setQuantite(aliment.getQuantite());
            a.setHeure(aliment.getHeure());
            a.setProduct(aliment.getProduct());
            a.setAgeMin(aliment.getAgeMin());
            a.setAgeMax(aliment.getAgeMax());
            a.setCategory(aliment.getCategory());
            a.setUser(userService.connecteduser(session));
            alimentationRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> update(Alimentation aliment, Long id){
        try{
            Alimentation a = alimentationRepository.findById(id).get();
            a.setQuantite(aliment.getQuantite());
            a.setHeure(aliment.getHeure());
            a.setProduct(aliment.getProduct());
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
