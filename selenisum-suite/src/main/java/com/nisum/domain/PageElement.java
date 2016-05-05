package com.nisum.domain;

import org.springframework.data.annotation.Id;

public class PageElement {

	@Id
	private String pageElementId;
	private String pageElementName;
	private String pageElementValue;
	private String pageElementType;

	public void setPageElementType(String pageElementType) {
		this.pageElementType = pageElementType;
	}

	public String getPageElementType() {
		return pageElementType;
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
