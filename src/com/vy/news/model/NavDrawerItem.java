package com.vy.news.model;

public class NavDrawerItem {
	String title;
	public NavDrawerItem(){}

	public NavDrawerItem(String title){
		this.title = title;
		
	}
	
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
}
