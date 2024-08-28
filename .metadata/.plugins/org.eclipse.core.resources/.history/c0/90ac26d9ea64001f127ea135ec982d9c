package com.kosta.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name="p_member_tbl")
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
public class PMember {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;
	
	// 프로젝트
	@JoinColumn(name="project_id")
	@ManyToOne
	private Project project;
	
	// 프로젝트에 속한 User id
	@JoinColumn(name="pmember_id")
	@ManyToOne
	private User member;
}
