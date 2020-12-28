package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ConfiguredModelMapper;
import com.example.demo.dto.PetCreating;
import com.example.demo.dto.PetInformation;
import com.example.demo.dto.PetSummary;
import com.example.demo.entity.Pet;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.PetService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@AllArgsConstructor
@Log
public class PetController {
	private final PetService petService;
	private final ConfiguredModelMapper modelMapper = new ConfiguredModelMapper();
	
	@PostMapping("/pets")
    public ResponseEntity<String> create(@RequestBody PetCreating petDto, 
    		                             Principal principal) throws ValidationException {
        log.info("Handling creating pet request: ");
        
        Integer userId = Integer.parseInt(principal.getName());
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet.setUserId(userId);
        petService.savePet(pet);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("my_pets")
    public List<PetSummary> findMyPets(Principal principal) {
        log.info("Handling find my pets request");
        
        int id = Integer.parseInt(principal.getName());
        
        List<Pet> pets = petService.findByUserId(id);
        List<PetSummary> petDtos = pets
        		  .stream()
        		  .map(pet -> modelMapper.map(pet, PetSummary.class))
        		  .collect(Collectors.toList());
        
        return petDtos;
    }
	
	@GetMapping("pets/findAll")
    public List<PetSummary> findAllPets() {
        log.info("Handling find all pets request");
        List<Pet> pets = petService.findAll();
        List<PetSummary> petDtos = pets
        		  .stream()
        		  .map(pet -> modelMapper.map(pet, PetSummary.class))
        		  .collect(Collectors.toList());
        
        return petDtos;
    }
	
	//Тварини на які користувач зробив запит
	@GetMapping("requested_pets")
    public List<PetSummary> findRequestedPets(Principal principal) {
        log.info("Handling find requested pets request");
        List<Pet> pets = petService.getRequestedPets(principal);
        List<PetSummary> petDtos = pets
        		  .stream()
        		  .map(pet -> modelMapper.map(pet, PetSummary.class))
        		  .collect(Collectors.toList());
        
        return petDtos;
    }
	
	@GetMapping("/pets/{id}")
    public ResponseEntity<PetInformation> getPetById(@PathVariable int id) {
        log.info("Handling get pet request: " + id);
        Optional<Pet> optPet = petService.findById(id);
		if(optPet.isEmpty()) return ResponseEntity.notFound().build();	
		PetInformation petInformation = modelMapper.map(optPet.get(), PetInformation.class);
		return ResponseEntity.ok().body(petInformation);	
      
    }
	
	@DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> deletePets(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}
