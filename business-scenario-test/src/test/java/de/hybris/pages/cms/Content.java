package de.hybris.pages.cms;


import de.hybris.pages.framework.enums.EditorLanguageEnum;

public class Content {
	
	private String language;
	private String textualContent;
	private String pathToImage;
	private String code;
	
	public Content(String language, String textualContent, String pathToImage, String code) {
		super();
		this.language = language;
		this.textualContent = textualContent;
		this.pathToImage = pathToImage;
		this.code = code;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTextualContent() {
		return textualContent;
	}

	public void setTextualContent(String textualContent) {
		this.textualContent = textualContent;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
