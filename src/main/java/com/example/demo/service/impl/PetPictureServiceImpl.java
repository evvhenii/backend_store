package com.example.demo.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Pet;
import com.example.demo.entity.PetPicture;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.PetPictureRepository;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetPictureService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PetPictureServiceImpl implements PetPictureService{
	private final PetPictureRepository petPictureRepository;
	private final PetRepository petRepository;
	
	@Override
	public boolean savePictures(List<PetPicture> petPictures, Principal principal) throws ValidationException {
		int userId = Integer.parseInt(principal.getName());
		System.out.println("userId = " + userId);
		for(PetPicture petPicture : petPictures) {
			int petId = petPicture.getPetId();
			System.out.println("petId = " + petId);
			Optional<Pet> petOpt = petRepository.findUserByPetId(petId);
			if(petOpt.isEmpty()) return false;
			int petOwnerId = petOpt.get().getUserId();
			System.out.println("petOwnerId = " + petOwnerId);
			if(petOwnerId != userId) return false;
		}
		petPictureRepository.saveAll(petPictures);
		return true;
	}
	
	@Override
	public List<PetPicture> findByUserId(Integer id) {
		List<PetPicture> petPictures = petPictureRepository.findByPetId(id);
        return petPictures;
	}
	
	
}
