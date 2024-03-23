package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Reproduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReproductionCategory extends JpaRepository<Reproduction, Long> {
}
