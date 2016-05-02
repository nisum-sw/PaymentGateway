package com.nisum.controller;

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

import com.nisum.domain.PageElement;
import com.nisum.repositories.PageElementRepository;

@RestController
@RequestMapping("/pageelements")
public class PageElementController {
	private PageElementRepository PageElementRepository;

	@Autowired
	public PageElementController(PageElementRepository PageElementRepository) {
		this.PageElementRepository = PageElementRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public PageElement create(@RequestBody @Valid PageElement PageElement) {
		return this.PageElementRepository.save(PageElement);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<PageElement> list() {
		System.out.println("NEWS");
		return this.PageElementRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PageElement get(@PathVariable("id") String id) {
		return this.PageElementRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public PageElement update(@PathVariable("id") long id, @RequestBody @Valid PageElement PageElement) {
		return PageElementRepository.save(PageElement);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
		this.PageElementRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
