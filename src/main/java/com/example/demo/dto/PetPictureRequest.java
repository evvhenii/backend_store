package com.example.demo.dto;

import lombok.Data;

@Data
public class PetPictureRequest {
	private String url;
    private Integer petId;
}
