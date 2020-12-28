package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ConfiguredModelMapper;
import com.example.demo.dto.PetInformation;
import com.example.demo.dto.RequestCreating;
import com.example.demo.dto.RequestGetting;
import com.example.demo.entity.Pet;
import com.example.demo.entity.Request;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.RequestService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@AllArgsConstructor
@Log
public class RequestController {
	private final RequestService requestService;
	private final ConfiguredModelMapper modelMapper = new ConfiguredModelMapper();

	@GetMapping("/pets/{petId}/requests")
    public List<RequestGetting> get(@PathVariable int petId,
    		                          Principal principal) throws ValidationException {
        log.info("Handling get requests request: ");   
        //Зробити додаткоу перевірку чи власник тварини надсилає запит
        //int userId = Integer.parseInt(principal.getName());
        
        List<Request> requests = requestService.findRequestsByPetId(petId);
        List<RequestGetting> requestDtos = requests
        		  .stream()
        		  .map(request -> modelMapper.map(request, RequestGetting.class))
        		  .collect(Collectors.toList());
        
        return requestDtos;
	}
	
	@PostMapping("/pets/{petId}/new_request")
    public ResponseEntity<String> create(@RequestBody RequestCreating requestCreating,
    		                             @PathVariable int petId,
    		                             Principal principal) throws ValidationException {
        log.info("Handling creating new request request: ");   
        
        Request request = modelMapper.map(requestCreating, Request.class);
        boolean wasCreated = requestService.createRequest(request, principal, petId);
        return wasCreated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
	
	@DeleteMapping("requests/{requestId}")
	public ResponseEntity<String> delete(@PathVariable int requestId, Principal principal){
		log.info("Handling deleting request request: ");   
      
        boolean wasDeleted = requestService.deleteRequest(principal, requestId);
        return wasDeleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
	}
	
	/*@PutMapping("/requests/{requestId}/answer")
    public ResponseEntity<String> change(@RequestBody RequestChange requestChange,
    		                             @PathVariable int requestId,
    		                             Principal principal) throws ValidationException {
        log.info("Handling change(answer) request request: ");   
        
        Request request = modelMapper.map(requestChange, Request.class);
        boolean wasChanged = requestService.changeRequest(request, principal, requestId);
        return wasChanged ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }*/
	
}
