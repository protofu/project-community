package com.kosta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.domain.UserDTO;
import com.kosta.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final UserService userService;
	
	// 회원가입 페이지
	@GetMapping("/join")
	public String joinPage() {
		return "user/join";
	}
	
	// 회원가입 동작
	@PostMapping("/join")
	public String join(UserDTO userDTO) {
		userService.join(userDTO);
		return "redirect:/";
	}
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
}
