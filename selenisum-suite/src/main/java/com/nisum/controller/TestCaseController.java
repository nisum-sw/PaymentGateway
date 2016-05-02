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

import com.nisum.domain.TestCase;
import com.nisum.repositories.TestCaseRepository;

@RestController
@RequestMapping("/testcases")
public class TestCaseController {
	private TestCaseRepository TestCaseRepository;

	@Autowired
	public TestCaseController(TestCaseRepository TestCaseRepository) {
		this.TestCaseRepository = TestCaseRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public TestCase create(@RequestBody @Valid TestCase TestCase) {
		return this.TestCaseRepository.save(TestCase);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TestCase> list() {
		return this.TestCaseRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TestCase get(@PathVariable("id") String id) {
		return this.TestCaseRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public TestCase update(@PathVariable("id") long id, @RequestBody @Valid TestCase TestCase) {
		return TestCaseRepository.save(TestCase);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
		this.TestCaseRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
