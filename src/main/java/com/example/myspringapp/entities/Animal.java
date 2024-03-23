package com.example.myspringapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Animal implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String sante;
    private Boolean vaccine;
    private double age;
    private double poids;
    private String photoUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference
    private Sexe sexe;
    @ManyToOne
    @JsonBackReference
    private Category category;
    @OneToOne(mappedBy = "animal")
    private Vente vente;

}
