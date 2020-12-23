package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetInformation {
private String name;
    
    private String category;
    
    private String gender;
    
    private String description;
    
    private String imageUrl;
    
    int daysAfterAdding;
    
    private Integer userId;
}
