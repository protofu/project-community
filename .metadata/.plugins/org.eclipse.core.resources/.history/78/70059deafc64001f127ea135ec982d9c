package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Project;
import com.kosta.repository.ProjectRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IProjectService implements ProjectService {
	private final ProjectRepo projectRepo;
	
	@Override
	public List<Project> findAll() {
		return projectRepo.findAll();
	}

	@Override
	public void save(Project project) {
		projectRepo.save(project);
	}

	@Override
	public void delete(Long id) {
		projectRepo.deleteById(id);
	}

	@Override
	public Project findById(Long id) {
		Optional<Project> optProject = projectRepo.findById(id);
		Project project = optProject.orElseThrow(() -> new IllegalArgumentException("해당 프로젝트를 찾을 수 없음"));
		return project;
	}

}
