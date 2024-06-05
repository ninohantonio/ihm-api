package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    Collection<Vente> findByCategory(Category category);
}
