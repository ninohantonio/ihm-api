package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}
