package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column
    private String message;
    
    @Column
    private String contactInfo;

    @Column
    private int userId;
    
    @Column
    private int petId;
    
    @Column
    private LocalDateTime requestDate;
}






