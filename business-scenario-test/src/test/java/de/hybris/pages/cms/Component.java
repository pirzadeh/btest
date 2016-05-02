package de.hybris.pages.cms;

import java.util.List;

public class Component {

	private String id;
	private String type;
	private String slotId;
	private List<Content> contentList;

	public Component(String id, String type, String slotId, List<Content> contentList) {
		super();
		this.id = id;
		this.type = type;
		this.slotId = slotId;
		this.contentList = contentList;
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
}
