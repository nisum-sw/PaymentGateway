package com.nisum.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Project {

	@Id
	private String id;

	private List<TestSuite> testSuite;
	private String pageURL;
	private String pageName;
	private String brwType;
	private String domainName;
	

	public Project() {
	}

	public Project(String domainName, List<TestSuite> testSuite, String pageURL, String pageName, String brwType) {
		this.testSuite = testSuite;
		this.pageURL = pageURL;
		this.pageName = pageName;
		this.brwType = brwType;
		this.domainName = domainName;

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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getBrwType() {
		return brwType;
	}

	public void setBrwType(String brwType) {
		this.brwType = brwType;
	}

	public String getDomainName() {
		if (domainName == null) {
			domainName = "undefined";
		}
		this.domainName = getFilteredDomainName(domainName);
		return domainName;
	}

	public void setDomainName(String domainName) {
		if (domainName == null) {
			domainName = "www.undefined.com";
		}
		this.domainName = domainName;
		this.domainName = setAppendedDomainName(domainName);
	}

	public String setAppendedDomainName(String domainName) {
		this.domainName = domainName;
		StringBuilder sb = new StringBuilder();
		sb.append("www.").append(domainName).append(".com");
		String strDmnName = sb.toString();
		return strDmnName;
	}

	public String getFilteredDomainName(String domainName) {
		this.domainName = domainName;
		int i = domainName.indexOf('.');
		String strBefore = domainName.substring(domainName.indexOf('.') + 1);
		String strAfter = (String) strBefore.subSequence(0, strBefore.indexOf('.'));
		return strAfter;
	}

}
