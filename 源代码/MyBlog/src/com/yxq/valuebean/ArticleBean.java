package com.yxq.valuebean;

public class ArticleBean {
	private int id=-1;
	private int typeId=-1;
	private String title="";
	private String content="";
	private String sdTime="";
	private String create="";
	private String info="";
	private int review=0;
	private int count=0;	
	
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getContent() {
		return content;
	}
	public String getContent(int len){
		if(len<=0||len>content.length())
			len=content.length();
		return content.substring(0,len)+"...";
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSdTime() {
		return sdTime;
	}
	public void setSdTime(String sdTime) {
		this.sdTime = sdTime;
	}
	public String getTitle() {
		return title;
	}
	public String getTitle(int len){
		if(len<=0||len>title.length())
			len=title.length();
		return title.substring(0,len)+"...";
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}	
}
