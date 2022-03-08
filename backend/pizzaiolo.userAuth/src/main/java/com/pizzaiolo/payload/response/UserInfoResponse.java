package com.pizzaiolo.payload.response;

import java.util.Date;
import java.util.List;

import com.pizzaiolo.models.Address;

public class UserInfoResponse {
	private String id;
	private String name;
	private String lastName;
	private Date birthDate;
	private String email;
	private List<Address> address;
	private List<String> roles;
	private boolean active;

	public UserInfoResponse(String id, String name, String lastName, Date birthDate, String email,
			List<Address> address, List<String> roles, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.address = address;
		this.roles = roles;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}