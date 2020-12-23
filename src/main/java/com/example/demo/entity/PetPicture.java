package com.example.demo.entity;

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
@Table(name = "pet_pics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetPicture {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petPicId;

    @Column
    private String url;
    
    @Column
    private Integer petId;

}
