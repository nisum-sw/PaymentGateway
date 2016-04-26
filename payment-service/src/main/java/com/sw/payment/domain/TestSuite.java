package com.sw.payment.domain;

import java.util.List;

public class TestSuite {
	private String testSuiteId;
    private String testSuiteName;
    private String testSuiteDesc;
    private List<TestCase> testCaseList;
    
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
