package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Collection<Animal> findByCategoryAndSante(Category category, String sante);
    Collection<Animal> findByCategoryAndVaccine(Category category, Boolean vaccine);
    Collection<Animal> findByCategoryAndDescriptionContainingIgnoreCase(Category category, String description);
    Collection<Animal> findByCategory(Category category, Sort sort);
}
