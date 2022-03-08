package com.pizzaiolo.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Address {
	@Id
	private String id;

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

	public Address(String id, @NotBlank @Size(max = 255) String street, @NotBlank int number,
			@NotBlank @Size(max = 150) String city, @NotBlank @Size(max = 150) String location,
			@NotBlank int postalCode, @NotBlank @Size(max = 150) String name) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.location = location;
		this.postalCode = postalCode;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
