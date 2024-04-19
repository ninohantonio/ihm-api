package com.example.myspringapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AnimalDto {
    private Long id;
    private String description;
    private String sante;
    private Boolean vaccine;
    private double age;
    private double poids;
    private String photoUrl;
    private Long user;
    private Long sexe;
    private Long category;
    private Long vente;
}
