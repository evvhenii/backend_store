package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petId;

    @Column
    private String name;
    
    @Column
    private String category;
    
    @Column
    private String imageUrl;

    @Column
    private LocalDateTime addingDate = LocalDateTime.now();
    
    @Column
    private String gender;
    
    @Column
    private String description;
    
    @Column
    private Integer userId;
    
    @Column
    private boolean available;
}
