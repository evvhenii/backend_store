package com.example.demo.service;

import java.security.Principal;

import com.example.demo.entity.Request;

public interface RequestService {

	boolean createRequest(Request request, Principal principal, int petId);

	boolean deleteRequest(Principal principal, int petId);

	boolean changeRequest(Request request, Principal principal, int requestId);

}
