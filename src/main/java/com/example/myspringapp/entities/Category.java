package com.example.myspringapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Category implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int dureeMoyen;
    private int prix;
    @ManyToOne
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "category")
    private Collection<Animal> animals;
//    @OneToMany(mappedBy = "category")
//    private Collection<Vaccination> vaccinations;
}
