package com.example.demo.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Pet;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PetServiceImpl implements PetService {
	private final PetRepository petRepository;

	@Override
	public Pet savePet(Pet pet) throws ValidationException {
		pet.setAvailable(true);
		petRepository.save(pet);
		return pet;
	}

	@Override
	public Optional<Pet> findById(Integer id) {
		Optional<Pet> pet = petRepository.findById(id);
        return pet;
	}
	
	@Override
	public List<Pet> findByUserId(Integer id) {
		List<Pet> pets = petRepository.findByUserId(id);
        return pets;
	}

	@Override
	public List<Pet> findAll() {
		List<Pet> pets = petRepository.findAvailablePets();
		return pets;
	}
	
	@Override
	@Transactional
	public void deletePet(Integer petId) {
		petRepository.changeToUnavailable(petId);
	}
	
	@Override
	public List<Pet> getRequestedPets(Principal principal) {
		int userId = Integer.parseInt(principal.getName());
		
		List<Pet> pets = petRepository.findPetsRequestedByUserId(userId);
        return pets;
	}

}
