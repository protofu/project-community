package com.kosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.Community;

@Repository
public interface CommunityRepo extends JpaRepository<Community, Long>{

}
