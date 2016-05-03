package de.hybris.data;

import java.util.List;

public class Component {

	private String id;
	private String type;
	private List<Content> contentList;
	private String slotId;
	private String pageId;
	private String catalogVersion;
	private String catalogId;
	

	public Component(String id, String type, String slotId, List<Content> contentList, String pageId, String catalogVersion, String catalogId) {
		super();
		this.id = id;
		this.type = type;
		this.pageId = 
		this.slotId = slotId;
		this.contentList = contentList;
		this.pageId = pageId;
		this.catalogVersion = catalogVersion;
		this.catalogId = catalogId;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Content> getContentList() {
		return contentList;
	}
	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


	public String getCatalogVersion() {
		return catalogVersion;
	}


	public void setCatalogVersion(String catalogVersion) {
		this.catalogVersion = catalogVersion;
	}


	public String getCatalogId() {
		return catalogId;
	}


	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}	
}
