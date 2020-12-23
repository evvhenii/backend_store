package com.example.demo.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Pet;
import com.example.demo.exception.ValidationException;

public interface PetService {
	Pet savePet(Pet pet) throws ValidationException;
	Optional<Pet> findById(Integer id);
    List<Pet> findAll();
    void deletePet(Integer petId);
	List<Pet> findByUserId(Integer id);
	List<Pet> getRequestedPets(Principal principal);
}
