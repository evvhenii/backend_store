package com.example.demo.service;

import java.security.Principal;
import java.util.List;

import com.example.demo.entity.PetPicture;
import com.example.demo.exception.ValidationException;

public interface PetPictureService {

	List<PetPicture> findByUserId(Integer id);

	boolean savePictures(List<PetPicture> petPictures, Principal principal) throws ValidationException;

}
