package com.kosta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.Project;

@Repository
public interface MyProjectRepo extends JpaRepository<Project, Long>{
	List<Project> findAllById(Long id);
}
