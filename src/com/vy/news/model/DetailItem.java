package com.vy.news.model;

public class DetailItem {

	 private String icon;
	 private String title;
	 private String description;
	 private String contentSnippet;
	 
	 public DetailItem(){
		  
	 }
	 
	 public DetailItem(String icon,String title,String description,String contentSnippet){
		  this.icon=icon;
		  this.title=title;
		  this.description=description;
		  this.setContentSnippet(contentSnippet);
		 }
		 
		 public String getIcon() {
		  return icon;
		 }
		 public void setIcon(String icon) {
		  this.icon = icon;
		 }
		 public String getTitle() {
		  return title;
		 }
		 public void setTitle(String title) {
		  this.title = title;
		 }
		 public String getdescription() {
			  return description;
		 }
		 public void setdescription(String description) {
			  this.description = description;
			 }

		

		public String getContentSnippet() {
			return contentSnippet;
		}

		public void setContentSnippet(String contentSnippet) {
			this.contentSnippet = contentSnippet;
		}
			 
			
}
