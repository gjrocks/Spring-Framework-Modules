package com.gj.model;

public class ChatMessage {

	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ChatMessage(String text) {
		super();
		this.text = text;
	}

	public ChatMessage() {
		super();
	
	}

	@Override
	public String toString() {
		return "ChatMessage [text=" + text + "]";
	}
	
	
}
