package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexeRepository extends JpaRepository<Sexe, Long> {
}
