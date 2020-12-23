package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PetPicture;

public interface PetPictureRepository extends JpaRepository<PetPicture, Integer> {

	List<PetPicture> findByPetId(Integer id);

}
