package com.blogging.spring.payload;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//it is a user payload to get user data by api and don't direct interact with entity class
//we get through this class to interact with entity class

@Component
public class UserDTO {

	private int Id;

	@NotEmpty(message = "Username must not be empty !!")
	@Size(min = 4, message = "Username must be above 4 letter !!")
	private String name;

	@Email
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email Pattern must be like Username@domain.com")
	private String email;

	@NotEmpty(message = "Password must not be empty !!")
	@Size(min = 6, max = 12, message = "Password lenght should be lie between 6 to 12 character !!")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "username must be of 6 to 12 length with no special characters")
	private String password;

	@NotEmpty(message = "About must not be empty !!")
	private String about;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
