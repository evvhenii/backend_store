package com.example.demo.config;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.PetInformation;
import com.example.demo.dto.UserProfile;
import com.example.demo.entity.Pet;
import com.example.demo.entity.User;


public class ConfiguredModelMapper extends ModelMapper {	
	@Autowired
	public ConfiguredModelMapper() {
		super.createTypeMap(User.class, UserProfile.class)
	    .addMappings(mapper -> mapper.
	    		using(user -> calcAge((LocalDate) user.getSource()))
	            .map(User::getBirthDate, UserProfile::setAge));
		
		super.createTypeMap(Pet.class, PetInformation.class)
	    .addMappings(mapper -> mapper.
	    		using(pet -> calcDaysAfterAdding((LocalDateTime) pet.getSource()))
	            .map(Pet::getAddingDate, PetInformation::setDaysAfterAdding));
		
		super.validate();
	}
	
	public int calcAge(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
    }
	
	public int calcDaysAfterAdding(LocalDateTime addingDate) {
		LocalDateTime currentDate = LocalDateTime.now();
		return (int)ChronoUnit.DAYS.between(addingDate, currentDate);
    }
}
