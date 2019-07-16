package com.yxq.valuebean;

public class WordBean {
	private int id;
	private String wordTitle;
	private String wordContent;
	private String wordTime;
	private String wordAuthor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWordAuthor() {
		return wordAuthor;
	}
	public void setWordAuthor(String wordAuthor) {
		this.wordAuthor = wordAuthor;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	public String getWordTime() {
		return wordTime;
	}
	public void setWordTime(String wordTime) {
		this.wordTime = wordTime;
	}
	public String getWordTitle() {
		return wordTitle;
	}
	public String getWordTitle(int len){
		if(len<=0||len>wordTitle.length())
			len=wordTitle.length();
		return wordTitle.substring(0,len)+"...";
	}
	public void setWordTitle(String wordTitle) {
		this.wordTitle = wordTitle;
	}
}
