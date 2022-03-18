package com.pizzaiolo.authorization.models.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Document(collection = "users")
public class Address {
	@NotBlank
	@Size(max = 255)
	private String street;
	
	@NotBlank
	private int number;
	
	@NotBlank
	@Size(max = 150)
	private String city;

	@NotBlank
	@Size(max = 150)
	private String location;

	@NotBlank
	private int postalCode;
	
	@NotBlank
	@Size(max = 150)
	private String name;

	public Address(@NotBlank @Size(max = 255) String street, @NotBlank int number,
			@NotBlank @Size(max = 150) String city, @NotBlank @Size(max = 150) String location,
			@NotBlank int postalCode, @NotBlank @Size(max = 150) String name) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.location = location;
		this.postalCode = postalCode;
		this.name = name;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}
}