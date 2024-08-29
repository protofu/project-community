package com.kosta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kosta.domain.UserRole;
import com.kosta.entity.PMember;
import com.kosta.entity.Project;
import com.kosta.entity.User;
import com.kosta.repository.PMemberRepo;
import com.kosta.repository.ProjectRepo;
import com.kosta.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IProjectService implements ProjectService {
	private final ProjectRepo projectRepo;
	private final PMemberRepo pMemberRepo;
	private final UserRepo userRepo;
	
	@Override
	public List<Project> findAll() {
		return projectRepo.findAll();
	}

	@Override
	public void save(Project project, User user) throws Exception {
		if (project.getEndAt().isBefore(project.getStartAt())) {
			throw new Exception("시작일과 종료일을 다시 설정하세요");
		}
		projectRepo.save(project);
		// ROLE이 USER면 MEMBER로 바꾸기
		if (user.getRole().equals(UserRole.USER) || user.getRole().equals(UserRole.MEMBER)) {
			user.setRole(UserRole.MAINTAINER);
			user = userRepo.save(user);
			// 권한 실시간 반영
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
			updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + UserRole.MAINTAINER));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
	}

	// 프로젝트 지우기
	@Override
	public void delete(Long id) {
		projectRepo.deleteById(id);
	}

	// 프로젝트 ID로 찾기
	@Override
	public Project findById(Long id) {
		Optional<Project> optProject = projectRepo.findById(id);
		Project project = optProject.orElseThrow(() -> new IllegalArgumentException("해당 프로젝트를 찾을 수 없음"));
		return project;
	}

	// 프로젝트 ID 를 통해 PMember에서 그 프로젝트에 속한 멤버 찾기
	@Override
	public List<PMember> findAllByProjectId(Long pId) {
		List<PMember> memList = pMemberRepo.findAllByProjectId(pId);
		return memList;
	}

}
