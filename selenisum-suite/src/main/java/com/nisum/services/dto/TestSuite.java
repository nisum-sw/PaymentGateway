package com.nisum.services.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

public class TestSuite {

	@Id
	private String id;

	private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;

	private List<TestCase> testCaseList;

	public TestSuite() {
	}

	public TestSuite(String testSuiteId, String testSuiteName,
			String testSuiteDesc, List<TestCase> testCaseList) {
		this.testSuiteId = testSuiteId;
		this.testSuiteName = testSuiteName;
		this.testSuiteDesc = testSuiteDesc;
		this.testCaseList = testCaseList;
	}

	public String getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(String testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getTestSuiteDesc() {
		return testSuiteDesc;
	}

	public void setTestSuiteDesc(String testSuiteDesc) {
		this.testSuiteDesc = testSuiteDesc;
	}

	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(List<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}

}
