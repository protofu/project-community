package com.kosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

}
