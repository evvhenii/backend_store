package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyProfile {
    private String name;
    private String surname;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String imageUrl;
}
