package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	private Integer id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String imageUrl;
}
