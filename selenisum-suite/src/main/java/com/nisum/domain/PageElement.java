package com.nisum.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class PageElement {

	@Id
	private String id;

	private String pageElementId;
	private String pageElementName;
	private String pageElementDesc;

	public PageElement() {
	}

	public PageElement(String pageElementId, String pageElementName,
			String pageElementDesc) {
		this.pageElementId = pageElementId;
		this.pageElementName = pageElementName;
		this.pageElementDesc = pageElementDesc;
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
		return pageElementDesc;
	}

	public void setPageElementDesc(String pageElementDesc) {
		this.pageElementDesc = pageElementDesc;
	}

}
