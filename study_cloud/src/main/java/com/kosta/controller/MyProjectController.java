package com.kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.domain.UserRole;
import com.kosta.entity.PMember;
import com.kosta.entity.Project;
import com.kosta.entity.User;
import com.kosta.repository.UserRepo;
import com.kosta.service.MyProjectService;
import com.kosta.service.PMemberService;
import com.kosta.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myproject/*")
public class MyProjectController {
	private final ProjectService projectService;
	private final MyProjectService myProjectService;
	private final PMemberService pMemberService;
	
	private final UserRepo userRepo;
	
	// 프로젝트 참가
	@PostMapping("/join")
	public String joinProject(PMember pMember, @RequestParam("projectId") Long pId, @AuthenticationPrincipal User user) {
		pMember.setMember(user);
		pMember.setProject(projectService.findById(pId));
		pMemberService.save(pMember);
		// ROLE이 USER면 MEMBER로 바꾸기
		if (user.getRole().equals(UserRole.USER)) {
			user.setRole(UserRole.MEMBER);
			user = userRepo.save(user);
			// 권한 실시간 반영
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
			updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + UserRole.MEMBER));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return "redirect:/project/list";
	}
	
	// 내가 참가하고 있는 프로젝트 목록
	@GetMapping("/list")
	public String myProjectListPage(@AuthenticationPrincipal User user, Model model) {
		List<Project> myProjectList = myProjectService.findAllById(user.getId());
		model.addAttribute("myProjectList", myProjectList);
		System.out.println(myProjectList);
		return "myproject/list";
	}
	
	// 프로젝트 나가기
	@DeleteMapping("/leave")
	public String leave(@RequestParam("projectId") Long pId, @AuthenticationPrincipal User user) {
		
		myProjectService.leave(user.getId(), pId);
		return "redirect:/project/list";
	}
}
