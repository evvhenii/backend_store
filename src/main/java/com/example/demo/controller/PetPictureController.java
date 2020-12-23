package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ConfiguredModelMapper;
import com.example.demo.dto.PetPictureRequest;
import com.example.demo.dto.PetPictureResponse;
import com.example.demo.entity.PetPicture;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.PetPictureService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@AllArgsConstructor
@Log
public class PetPictureController {
	private final PetPictureService petPictureService;
	private final ConfiguredModelMapper modelMapper = new ConfiguredModelMapper();
	
	@GetMapping("pet/{id}/pet_pictures")
    public List<PetPictureResponse> findPetsPictures(@PathVariable int id) {
        log.info("Handling find pets all pictures request");
        List<PetPicture> petPictures = petPictureService.findByUserId(id);
        petPictures.stream().forEach(System.out::println);
       
        List<PetPictureResponse> petPicturesDto = petPictures
        		  .stream()
        		  .map(petPicture -> modelMapper.map(petPicture, PetPictureResponse.class))
        		  .collect(Collectors.toList());
        
        return petPicturesDto;
    }
	
	@PostMapping("/pet_pictures")
    public ResponseEntity<String> create(@RequestBody List<PetPictureRequest> petPictureRequest, 
    		                             Principal principal) throws ValidationException {
        log.info("Handling creating pets pictures request: ");   
        List<PetPicture> petPictures = petPictureRequest
      		  .stream()
      		  .map(petPicture -> modelMapper.map(petPicture, PetPicture.class))
      		  .collect(Collectors.toList());
      
        boolean wereCreated = petPictureService.savePictures(petPictures, principal);
        System.out.println(wereCreated);
        return wereCreated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
