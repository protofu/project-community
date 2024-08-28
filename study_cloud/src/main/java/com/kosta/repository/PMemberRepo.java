package com.kosta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.PMember;

@Repository
public interface PMemberRepo extends JpaRepository<PMember, Long> {

	List<PMember> findAllByMemberId(Long id);

	void deleteByMemberIdAndProjectId(Long id, Long pId);

	List<PMember> findAllByProjectId(Long pId);
}
