package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {
	@Query(	value = "SELECT p.user_id FROM requests r JOIN pets p ON(r.pet_id = p.pet_id) WHERE request_id = ?;",
			nativeQuery = true)
	int findPetOwnerIdByRequestId(int requestId);

}
