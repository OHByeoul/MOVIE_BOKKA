package com.grade.vo;

import java.util.ArrayList;
import java.util.List;

public class GetMovieInfoForm {
	private int m_code;
	private String m_title;
	private String m_img;
	private float m_userRating;
	private String m_story;
	private List<String> actor;
	private List<String> director;
	
	public GetMovieInfoForm() {
		actor = new ArrayList<String>();
		director = new ArrayList<String>();
	}
	
	public int getM_code() {
		return m_code;
	}

	public void setM_code(int m_code) {
		this.m_code = m_code;
	}

	public String getM_title() {
		return m_title;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public String getM_img() {
		return m_img;
	}

	public void setM_img(String m_img) {
		this.m_img = m_img;
	}

	public float getM_userRating() {
		return m_userRating;
	}

	public void setM_userRating(float m_userRating) {
		this.m_userRating = m_userRating;
	}

	public String getM_story() {
		return m_story;
	}

	public void setM_story(String m_story) {
		this.m_story = m_story;
	}

	public List<String> getActor() {
		return actor;
	}

	public void setActor(List<String> actor) {
		this.actor = actor;
	}

	public List<String> getDirector() {
		return director;
	}

	public void setDirector(List<String> director) {
		this.director = director;
	}

}
