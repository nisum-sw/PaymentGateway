package com.sw.payment.domain;

import java.util.List;

public class TestCase {
	private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private List<PageElement> pageElement;

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
