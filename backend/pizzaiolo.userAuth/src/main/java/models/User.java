package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;

	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 20)
	private String lastName;
	
	private Date birthDate;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	//Direcciones

	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	private boolean active;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
