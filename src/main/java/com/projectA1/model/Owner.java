package com.projectA1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="f_owner")
@Getter @Setter
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String businessRegistrationNumber;
	private String ownerName;
	private String centerName;
	private String centerAddr;
	private String ownerPhoneNumber;
	

}
