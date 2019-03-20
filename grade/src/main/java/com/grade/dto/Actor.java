package com.grade.dto;

public class Actor{
	private int id;
	private int code;
	private String name;
	
	public Actor(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return this.code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
}
