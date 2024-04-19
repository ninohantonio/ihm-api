package com.example.myspringapp.services;

import com.example.myspringapp.dao.AnimalRepository;
import com.example.myspringapp.dao.CategoryRepository;
import com.example.myspringapp.dao.SexeRepository;
import com.example.myspringapp.dao.UserRepository;
import com.example.myspringapp.dto.AnimalDto;
import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.Sexe;
import com.example.myspringapp.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class AnimalService{
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SexeRepository sexeRepository;

    public Collection<Animal> parcategorie(Long id){
        Category category = categoryRepository.findById(id).get();
        return category.getAnimals();
    }

    public Collection<Animal> parsexe(Long sexeid, Long category){
        Sexe sexe = sexeRepository.findById(sexeid).get();
        Category category1 = categoryRepository.findById(category).get();
        return animalRepository.findAll().stream()
                .filter(animal -> sexe.equals(animal.getSexe()) && category1.equals(animal.getCategory()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> insert(AnimalDto animal, HttpSession session){
        try{
            Animal a = new Animal();
            Category c = categoryRepository.findById(animal.getCategory()).get();
            Sexe s = sexeRepository.findById(animal.getSexe()).get();
            a.setVaccine(animal.getVaccine());
            a.setCategory(c);
            a.setSexe(s);
            a.setAge(animal.getAge());
            a.setSante(animal.getSante());
            a.setPoids(animal.getPoids());
            a.setDescription(animal.getDescription());
            a.setUser(userService.connecteduser(session));
            animalRepository.save(a);
            return new ResponseEntity<>(a, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }

    }

    public ResponseEntity<?> specific(Long id){
        try{
            return ResponseEntity.ok(animalRepository.findById(id).get());
        }catch (Exception e){
            return new ResponseEntity<>("error "+ e, HttpStatusCode.valueOf(500));
        }
    }

    public void insertphoto(MultipartFile file, Long id) throws Exception{
        try{
            Animal a = animalRepository.findById(id).get();
            a.setPhotoUrl(a.getCategory().getName() + "." + id + ".jpg");
            Files.write(Paths.get(System.getProperty("user.home") + "/fermedata/animals/" + a.getPhotoUrl()), file.getBytes());
            animalRepository.save(a);
        }catch (Exception e){
            System.out.println("Error insert file" + e.getMessage());
        }
    }

    public byte[] getphoto(Long id) throws Exception {
        Animal a = animalRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/fermedata/animals/" + a.getPhotoUrl()));
    }

    public ResponseEntity<?> update(AnimalDto animal, Long id){
        try{
            Animal a = animalRepository.findById(id).get();
            Sexe s = sexeRepository.findById(animal.getSexe()).get();
            a.setVaccine(animal.getVaccine());
            a.setAge(animal.getAge());
            a.setDescription(animal.getDescription());
            a.setPoids(animal.getPoids());
            a.setSexe(s);
            a.setSante(animal.getSante());
            Animal save = animalRepository.save(a);
            return new ResponseEntity<>(save, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }

    }

    public Boolean delete(Long id){
        try{
            animalRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}
