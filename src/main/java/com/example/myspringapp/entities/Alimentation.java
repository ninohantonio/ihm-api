package com.example.myspringapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Alimentation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
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
