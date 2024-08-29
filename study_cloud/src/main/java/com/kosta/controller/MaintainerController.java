package com.kosta.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.entity.Project;
import com.kosta.entity.User;
import com.kosta.service.MaintainerService;
import com.kosta.service.MyProjectService;
import com.kosta.service.PMemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/maintainer/*")
public class MaintainerController {
	private final MaintainerService maintainerService;
	private final MyProjectService myProjectService;
	private final PMemberService pMemberService;

	@GetMapping("**")
	public String maintainerPage(Model model, @AuthenticationPrincipal User user) {
		List<Project> myProjectList = myProjectService.findAllById(user.getId());
		model.addAttribute("myProjectList", myProjectList);
		return "maintainer/maintainer";
	}
}
