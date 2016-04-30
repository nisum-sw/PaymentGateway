package com.nisum.domain;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {

	private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;
	private List<TestCase> testCases;
	private Long time;
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
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
	public List<TestCase> getTestCases() {
		if(this.testCases == null){
			this.testCases = new ArrayList<TestCase>();
		}
	
		return testCases;
	}
	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
}
