package com.nisum.services.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

public class TestCase {
	@Id
	private String id;

	private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private List<PageElement> pageElement;

	public TestCase() {
	}

	public TestCase(String testCaseId, String testCaseName,
			String testCaseDesc, List<PageElement> pageElement) {
		this.testCaseId = testCaseId;
		this.testCaseName = testCaseName;
		this.testCaseDesc = testCaseDesc;
		this.pageElement = pageElement;
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

	public List<PageElement> getPageElement() {
		return pageElement;
	}

	public void setPageElement(List<PageElement> pageElement) {
		this.pageElement = pageElement;
	}
}
