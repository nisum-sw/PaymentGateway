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

import com.nisum.domain.TestCase;
import com.nisum.repositories.TestCaseRepository;

@RestController
@RequestMapping("/testcases")
public class TestCaseController {
	private TestCaseRepository testSuiteRespository;

	@Autowired
	public TestCaseController(TestCaseRepository testSuiteRespository) {
		this.testSuiteRespository = testSuiteRespository;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<TestCase> create(@RequestBody TestCase TestCase) {
		TestCase testCase = testSuiteRespository.save(TestCase);
		return new ResponseEntity<TestCase>(testCase, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TestCase>> list() {
		List<TestCase> lstTestCase = this.testSuiteRespository.findAll();
		return new ResponseEntity(lstTestCase, HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TestCase>> get(@RequestParam("id") String id) {
		TestCase testsuite = this.testSuiteRespository.findOne(id);
		return new ResponseEntity(testsuite, HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<TestCase> update(@RequestParam("id") long id, @RequestBody @Valid TestCase TestCase) {
		TestCase testCase = testSuiteRespository.save(TestCase);
		return new ResponseEntity<TestCase>(testCase, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@RequestParam("id") String id) {
		this.testSuiteRespository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

}
