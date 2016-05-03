package com.nisum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.domain.PageElement;
import com.nisum.repositories.PageElementRepository;

@RestController
@RequestMapping("/pagelements")
public class PageElementController {
	private PageElementRepository PageElementRespository;

	@Autowired
	public PageElementController(PageElementRepository PageElementRespository) {
		this.PageElementRespository = PageElementRespository;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<PageElement> create(@RequestBody PageElement PageElement) {
		PageElement pageElement = PageElementRespository.save(PageElement);
		return new ResponseEntity<PageElement>(pageElement, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<PageElement>> list() {
		List<PageElement> lstPageElement = this.PageElementRespository.findAll();
		return new ResponseEntity(lstPageElement, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PageElement>> get(@RequestParam("id") String id) {
		PageElement testsuite = this.PageElementRespository.findOne(id);
		return new ResponseEntity(testsuite, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<PageElement> update(@RequestParam("id") long id,
			@RequestBody @Valid PageElement PageElement) {
		PageElement pageElement = PageElementRespository.save(PageElement);
		return new ResponseEntity<PageElement>(pageElement, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam("id") String id) {
		this.PageElementRespository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

}
