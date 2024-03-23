package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Alimentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentationRepository extends JpaRepository<Alimentation, Long> {
}
