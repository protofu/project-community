package com.kosta.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name="community_tbl")
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
public class Community {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;
	
	@JoinColumn(name="creator_id")
	@ManyToOne
	private User creator;
	
	@Column(nullable=false)
	private String content;
	
	@CreatedDate
	@Column(name="created_at")
	private LocalDateTime createAt;
	
	@Column(name="is_deleted")
	private String isDeleted;
}
