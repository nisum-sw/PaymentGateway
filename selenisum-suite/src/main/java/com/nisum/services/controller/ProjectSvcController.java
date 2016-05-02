package com.nisum.services.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.services.dto.Project;
import com.nisum.services.repository.ProjectRepository;

@RestController
@RequestMapping("/rest/projects")
public class ProjectSvcController {
	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectSvcController(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Project create(@RequestBody @Valid Project Project) {
		return this.projectRepository.save(Project);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Project> list() {
		System.out.println("NEWS");
		return this.projectRepository.findAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Project get(@PathVariable("id") String id) {
		return this.projectRepository.findOne(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Project update(@PathVariable("id") long id, @RequestBody @Valid Project Project) {
		return projectRepository.save(Project);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
		this.projectRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
