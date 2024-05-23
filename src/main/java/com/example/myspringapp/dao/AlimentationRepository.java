package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Alimentation;
import com.example.myspringapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Collection;

public interface AlimentationRepository extends JpaRepository<Alimentation, Long> {
    Collection<Alimentation> findByHeureAndCategory(LocalTime heure, Category category);
}
