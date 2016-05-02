package com.nisum.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class TestCase {

	@Id
	private String id;
	
	private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private List<TestStep> testSteps;
	private Long time;
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	public List<TestStep> getTestSteps() {
		if(this.testSteps == null){
			this.testSteps = new ArrayList<TestStep>();
		}
	
		return testSteps;
	}
	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}
}
