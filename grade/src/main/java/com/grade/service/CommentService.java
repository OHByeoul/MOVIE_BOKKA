package com.grade.service;

import java.sql.SQLException;

import com.grade.dao.BoardDAO;

public class CommentService {
	private BoardDAO boardDAO;
	
	public CommentService() {
		try {
			boardDAO = new BoardDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addComment() {
		
	}
	
	
}
