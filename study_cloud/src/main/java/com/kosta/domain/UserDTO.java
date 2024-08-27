package com.kosta.domain;

import com.kosta.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
	private Long id;
	private String name, username, email, password;
	private UserRole role;

	@Builder
	public UserDTO(Long id, String name, String username, String email, String password, UserRole role) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User setUser() {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
	
	
}
