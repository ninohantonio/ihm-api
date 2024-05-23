package com.example.myspringapp.dto;

import com.example.myspringapp.entities.Category;
import com.example.myspringapp.entities.Product;
import com.example.myspringapp.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class AlimentationDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime heure;
    private double quantite;
    private int ageMin;
    private int ageMax;
    private Long category;
    private Long product;
}
