package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
