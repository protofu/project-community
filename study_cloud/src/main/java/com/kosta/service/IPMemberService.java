package com.kosta.service;

import org.springframework.stereotype.Service;

import com.kosta.entity.PMember;
import com.kosta.repository.PMemberRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IPMemberService implements PMemberService {
	private final PMemberRepo pMemberRepo;
	
	@Override
	public void save(PMember pMember) {
		System.out.println(pMember);
		pMemberRepo.save(pMember);
	}
}
