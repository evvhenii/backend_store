package com.example.demo.service.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Pet;
import com.example.demo.entity.Request;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.service.RequestService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {
	private final RequestRepository requestRepository;
	private final PetRepository petRepository;
	
	@Override
	public boolean createRequest(Request request, Principal principal, int petId) {
		boolean petIsAvailable =petRepository.findAvailableByPetId(petId);
		if(!petIsAvailable) return false;
		
		Optional<Pet> petOpt = petRepository.findUserByPetId(petId);
		if(petOpt.isEmpty()) return false;
		
		int userId = Integer.parseInt(principal.getName());
		int petOwnerId = petOpt.get().getUserId();
		if(petOwnerId == userId) return false;
		
		request.setAccepted(null);
		request.setRequestDate(LocalDateTime.now());
		request.setUserId(userId);
		request.setPetId(petId);
		
		requestRepository.save(request);
        return true;
	}
	
	@Override
	public boolean deleteRequest(Principal principal, int requestId) {
		int userId = Integer.parseInt(principal.getName());
		int requestCreatorId = requestRepository.findPetOwnerIdByRequestId(requestId);
		if(requestCreatorId != userId) return false;
				
		requestRepository.deleteById(requestId);
        return true;
	}
	
	@Override
	@Transactional
	public boolean changeRequest(Request request, Principal principal, int requestId) {
		int userId = Integer.parseInt(principal.getName());
		int dogOwnerId = requestRepository.findPetOwnerIdByRequestId(requestId);
		if(userId != dogOwnerId) return false;
		Optional<Request> optNewRequest = requestRepository.findById(requestId);
		if(optNewRequest.isEmpty()) return false;
		Request newRequest = optNewRequest.get();
		newRequest.setAccepted(request.getAccepted());
		
		return true;
	}
}
