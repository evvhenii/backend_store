package com.example.demo.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.Data;

@Data
public class RequestGetting {
    private String message;
    private String contactInfo;
    private int userId;
    //private int petId;
    private LocalDateTime requestDate;
}
