package com.kosta.service;

import java.util.List;

import com.kosta.entity.Community;

public interface CommunityService {

	List<Community> findAll();

	void add(Community comm);

	Community findById(Long id);

	void delete(Long id);

}
