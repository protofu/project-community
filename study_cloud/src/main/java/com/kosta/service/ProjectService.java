package com.kosta.service;

import java.util.List;

import com.kosta.entity.PMember;
import com.kosta.entity.Project;
import com.kosta.entity.User;

public interface ProjectService {

	List<Project> findAll();

	void save(Project project, User user) throws Exception;

	void delete(Long id);

	Project findById(Long id);

	List<PMember> findAllByProjectId(Long pId);

}
