package com.example.demo.entity;


import java.time.LocalDate;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String name;
    
    @Column
    private String surname;

    @Column
    private String email;
    
    @Column
    private String imageUrl;
    
    @Column
    private String password;
    
    @Column
    private String gender;
    
    @Column
    private LocalDate birthDate;
}
