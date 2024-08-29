package com.kosta.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.entity.PMember;
import com.kosta.entity.Project;
import com.kosta.entity.User;
import com.kosta.service.MyProjectService;
import com.kosta.service.ProjectService;
import com.kosta.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project/*")
public class ProjectController {
	private final ProjectService projectService;
	private final MyProjectService myProjectService;
	private final UserService userService;
	
	// 전체 프로젝트 목록 
	@GetMapping("/list")
	public String listPage(@RequestParam(value="pId", required = false) Long pId , Model model, @AuthenticationPrincipal User user) {
		List<Project> projectList = projectService.findAll();
		List<Project> myPro = myProjectService.findAllById(user.getId());
		
		// myPro에 있는 프로젝트 ID 목록을 생성합니다
	    Set<Long> myProIds = myPro.stream()
	                              .map(Project::getId)
	                              .collect(Collectors.toSet());

	    // projectList에서 myPro에 포함된 프로젝트를 제외합니다
	    List<Project> filteredProjectList = projectList.stream()
	        .filter(project -> !myProIds.contains(project.getId()))
	        .collect(Collectors.toList());
	    
		model.addAttribute("list", filteredProjectList);
		model.addAttribute("user", user);
		model.addAttribute("myProjectList", myPro);
	    // pId가 제공되면 해당 프로젝트의 멤버 리스트를 추가로 조회
	    if (pId != null) {
	        List<PMember> memberList = projectService.findAllByProjectId(pId);
	        model.addAttribute("memberList", memberList);
	    }
		return "project/list";
	}
	
	// 프로젝트 추가
	@GetMapping("/add")
	public String addPage(Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "project/add";
	}
	
	// 프로젝트 추가 동작
	@PostMapping("/add")
	public String add(Project project, @RequestParam("maintainerId") Long maintainerId, @AuthenticationPrincipal User user) throws Exception {
		User maintainer = userService.findById(maintainerId);
		project.setMaintainer(maintainer);
		System.out.println(project.getStartAt());
		projectService.save(project, user);
		
		return "redirect:/project/list";
	}
	
	// 프로젝트 삭제
	@PostMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		projectService.delete(id);
		return "redirect:/project/list";
	}
	
	// 프로젝트 수정
	@GetMapping("/edit")
	public String editPage(@RequestParam("id") Long id, Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Project project = projectService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "project/edit";
	}
	
	// 프로젝트 수정 동작
	@PostMapping("/edit")
	public String edit(@RequestParam("id") Long id, Project project, @AuthenticationPrincipal User user) throws Exception {
		Project originProject = projectService.findById(id);
		originProject.setName(project.getName());
		originProject.setDescription(project.getDescription());
		originProject.setStartAt(project.getStartAt());
		originProject.setEndAt(project.getEndAt());
		projectService.save(originProject, user);
		return "redirect:/project/list";
	}
	
}
