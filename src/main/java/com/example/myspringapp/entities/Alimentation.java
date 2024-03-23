package com.example.myspringapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Alimentation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime heure;
    private double quantite;
    private int ageMin;
    private int ageMax;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Product product;
}
