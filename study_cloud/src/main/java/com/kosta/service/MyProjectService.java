package com.kosta.service;

import java.util.List;

import com.kosta.entity.Project;

public interface MyProjectService {

	List<Project> findAllById(Long id);

	void leave(Long id, Long pId);
}
