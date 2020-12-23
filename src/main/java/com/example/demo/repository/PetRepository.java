package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	@Modifying
	@Query(	value = "SELECT * FROM pets WHERE user_id = ?",
			nativeQuery = true)
	List<Pet> findByUserId(Integer id);

	Optional<Pet> findUserByPetId(int petId);

	@Modifying
	@Query(	value = "SELECT * FROM pets WHERE available = true",
			nativeQuery = true)
	List<Pet> findAvailablePets();
	
	@Query(	value = "SELECT * FROM pets WHERE pet_id = ? AND available = true",
			nativeQuery = true)
	Optional<Pet> findById(Integer id);
	
	@Query(	value = "SELECT available FROM pets WHERE pet_id = ?",
			nativeQuery = true)
	boolean findAvailableByPetId(Integer id);
	
	@Modifying
	@Query(	value = "UPDATE pets SET available = NOT available WHERE available=TRUE AND pet_id = ? ",
			nativeQuery = true)
	void changeToUnavailable(Integer id);

	@Query(	value = "SELECT p.* FROM pets p JOIN requests r ON(p.pet_id=r.request_id) WHERE r.user_id = ? ",
			nativeQuery = true)
	List<Pet> findPetsRequestedByUserId(int userId);
}
