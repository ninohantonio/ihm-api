package com.example.myspringapp.services;

import com.example.myspringapp.dao.VaccinationRepository;
import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.entities.Vaccination;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {
    @Autowired
    private VaccinationRepository vaccinationRepository;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> insert(Vaccination vaccination, HttpSession session){
        try{
            Vaccination a = new Vaccination();
            a.setDateVaccination(vaccination.getDateVaccination());
            a.setCategory(vaccination.getCategory());
            a.setUser(userService.connecteduser(session));
            vaccinationRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> update(Vaccination vaccination, Long id){
        try{
            Vaccination a = vaccinationRepository.findById(id).get();
            a.setDateVaccination(vaccination.getDateVaccination());
            a.setChecker(vaccination.getChecker());
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public void docheck(Long id){
        Vaccination a = vaccinationRepository.findById(id).get();
        a.setChecker(true);
        vaccinationRepository.save(a);
    }

    public Boolean delete(Long id){
        try {
            vaccinationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
