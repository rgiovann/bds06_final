package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.User;

public class UserDTO implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
	private long id;
	@NotBlank(message = "First name can´t be empty.")
	private String name;
	@Email( message = "Invalid email format.")	
	private String email;
	
	Set<RoleDTO> roles  = new HashSet<RoleDTO>();
	
	public UserDTO() {
	}


	public UserDTO(long id, String name,   String email ) {
		this.id = id;
		this.name = name;
 		this.email = email;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
 		this.email = user.getEmail();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
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
	
	public Set<RoleDTO> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
