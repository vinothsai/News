package com.vy.news.model;

public class CommentDrawer {
	 private String name;
	 private String comment;
	private String Title;
	

	 
	 public CommentDrawer(){
		  
	 }
	 
	 public CommentDrawer(String name,String comment){
		  this.setName(name);
		  this.setComment(comment);
		
		 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	
		 
		
}
