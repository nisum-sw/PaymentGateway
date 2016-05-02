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

import com.nisum.domain.TestSuite;
import com.nisum.repositories.TestSuiteRepository;

@RestController
@RequestMapping("/testsuites")
public class TestSuiteController {
	private TestSuiteRepository TestSuiteRepository;

	@Autowired
	public TestSuiteController(TestSuiteRepository TestSuiteRepository) {
		this.TestSuiteRepository = TestSuiteRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public TestSuite create(@RequestBody @Valid TestSuite TestSuite) {
		return this.TestSuiteRepository.save(TestSuite);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TestSuite> list() {
		System.out.println("NEWS");
		return this.TestSuiteRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TestSuite get(@PathVariable("id") String id) {
		return this.TestSuiteRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public TestSuite update(@PathVariable("id") long id, @RequestBody @Valid TestSuite TestSuite) {
		return TestSuiteRepository.save(TestSuite);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
		this.TestSuiteRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
