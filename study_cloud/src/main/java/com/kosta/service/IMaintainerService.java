package com.kosta.service;

import org.springframework.stereotype.Service;

import com.kosta.repository.MaintainerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IMaintainerService implements MaintainerService{
	private MaintainerRepo maintainerRepo;
}
