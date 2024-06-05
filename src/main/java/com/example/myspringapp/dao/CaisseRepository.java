package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Caisse;
import com.example.myspringapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    Caisse findByUser(User user);
}
