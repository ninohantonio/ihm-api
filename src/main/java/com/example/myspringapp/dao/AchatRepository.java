package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchatRepository extends JpaRepository<Achat, Long> {
}
