package com.nisum.domain;

import org.springframework.data.annotation.Id;

public class PageElement {

	@Id
	private String pageElementId;
	private String pageElementName;
	private String pageElementValue;

	public PageElement() {
	}

	public PageElement(String pageElementId, String pageElementName, String pageElementValue) {
		this.pageElementId = pageElementId;
		this.pageElementName = pageElementName;
		this.pageElementValue = pageElementValue;
	}

	public String getPageElementId() {
		return pageElementId;
	}

	public void setPageElementId(String pageElementId) {
		this.pageElementId = pageElementId;
	}

	public String getPageElementName() {
		return pageElementName;
	}

	public void setPageElementName(String pageElementName) {
		this.pageElementName = pageElementName;
	}

	public String getPageElementDesc() {
		return pageElementValue;
	}

	public void setPageElementDesc(String pageElementDesc) {
		this.pageElementValue = pageElementValue;
	}

}
