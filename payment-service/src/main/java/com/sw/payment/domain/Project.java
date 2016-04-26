package com.sw.payment.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Project {

	@Id
	private String id;
	private String projectName;
	private String projectDesc;
	private List<TestSuite> testSuite;
	private String pageURL;
	private List<String> pageName;
	private List<String> brwType;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TestSuite> getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(List<TestSuite> testSuite) {
		this.testSuite = testSuite;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public List<String> getPageName() {
		return pageName;
	}

	public void setPageName(List<String> pageName) {
		this.pageName = pageName;
	}

	public List<String> getBrwType() {
		return brwType;
	}

	public void setBrwType(List<String> brwType) {
		this.brwType = brwType;
	}

}
