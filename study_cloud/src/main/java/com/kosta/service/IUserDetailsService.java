package com.kosta.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kosta.entity.User;
import com.kosta.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IUserDetailsService implements UserDetailsService{
	private final UserRepo userRepo;
	
	// 로그인
	@Override
	public User loadUserByUsername(String username) {
		System.out.println("확인용 username  =  " + username);
		Optional<User> optUser = userRepo.findByUsername(username);
		System.out.println("확인용" + optUser);
		User user = optUser.orElseThrow(() -> new IllegalArgumentException(username + " 없음"));
		return user;
	}
}
