package com.gj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_projects")
public class UserProject {
	
	@Id
	@GeneratedValue
	private long id;
	private String text;
	private boolean completed;

	public UserProject() {
		super();
	}
	
	
	public UserProject(long id, String text, boolean completed) {
		super();
		this.id = id;
		this.text = text;
		this.completed = completed;
	}


	public UserProject(String text, boolean completed) {
		super();
		this.text = text;
		this.completed = completed;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
	

}
