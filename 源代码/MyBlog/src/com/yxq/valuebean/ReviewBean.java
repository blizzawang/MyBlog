package com.yxq.valuebean;

public class ReviewBean {
	private int id=0;
	private int articleId=0;
	private String reAuthor="";
	private String reContent="";
	private String reSdTime="";
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}	
	public String getReAuthor() {
		return reAuthor;
	}
	public void setReAuthor(String reAuthor) {
		this.reAuthor = reAuthor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReSdTime() {
		return reSdTime;
	}
	public void setReSdTime(String reSdTime) {
		this.reSdTime = reSdTime;
	}
}
