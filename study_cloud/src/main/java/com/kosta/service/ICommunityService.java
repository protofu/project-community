package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Community;
import com.kosta.repository.CommunityRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ICommunityService implements CommunityService {
	private final CommunityRepo commRepo;

	@Override
	public List<Community> findAll() {
		List<Community> commList = commRepo.findAll();
		return commList;
	}

	@Override
	public void add(Community comm) {
		commRepo.save(comm);
	}

	@Override
	public Community findById(Long id) {
		Optional<Community> optComm = commRepo.findById(id);
		Community comm = optComm.orElseThrow(() -> new IllegalArgumentException("잘못된 글입니다."));
		return comm;
	}

	@Override
	public void delete(Long id) {
		commRepo.deleteById(id);
	}
}
