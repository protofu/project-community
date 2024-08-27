package com.kosta.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.domain.UserDTO;
import com.kosta.entity.User;
import com.kosta.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IUserService implements UserService {
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void join(UserDTO userDTO) {
//		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//		userRepo.save(userDTO.setUser());
		String password = userDTO.getPassword();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		userDTO.setPassword(encodedPassword);
		userRepo.save(userDTO.setUser());
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> optUser = userRepo.findByUsername(username);
		User user = optUser.orElseThrow(() -> new IllegalArgumentException(username + " 없음"));
		return user;
	}

	@Override
	public User findById(Long maintainerId) {
		Optional<User> optUser = userRepo.findById(maintainerId);
		User user = optUser.orElseThrow(() -> new IllegalArgumentException(maintainerId + " 없음"));
		return user;

	}
}
