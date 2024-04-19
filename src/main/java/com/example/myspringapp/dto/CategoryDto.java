package com.example.myspringapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class CategoryDto {
    private Long id;
    private String name;
    private int dureeMoyen;
    private int prix;
}
