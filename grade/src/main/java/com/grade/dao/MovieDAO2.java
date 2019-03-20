//package com.grade.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.grade.dto.MovieInfo;
//
//public class MovieDAO2 {
//	private Connection conn;
//	List<MovieInfo> movieInfoLists;
//	
//	public MovieDAO2() throws SQLException {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "scott";
//			String password = "tiger";
//			conn = DriverManager.getConnection(url, user, password);
//			movieInfoLists = new ArrayList<MovieInfo>();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void InsertMovieInfo(MovieInfo movieInfo) {
//		String query = "INSERT INTO movieInfo VALUES (?,?,?,?,?,?)";
//		try {
//			PreparedStatement preparedStatement = conn.prepareStatement(query);
//			preparedStatement.setString(1, movieInfo.getCode());
//			preparedStatement.setString(2, movieInfo.getGenre());
//			preparedStatement.setString(3, movieInfo.getDirector());
//			preparedStatement.setString(4, movieInfo.getActor());
//			preparedStatement.setString(5, movieInfo.getStory());
//			preparedStatement.setString(6, movieInfo.getImg());
//			int result = preparedStatement.executeUpdate();
//			System.out.println("res : "+result);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public MovieInfo getMovieInfo(String s) {
//		String query = "SELECT * FROM movieInfo WHERE code = ?";
//		try {
//			PreparedStatement preparedStatement = conn.prepareStatement(query);
//			preparedStatement.setString(1, s);
//			ResultSet rs = preparedStatement.executeQuery();
//			while(rs.next()) {
//				String code = rs.getString("code");
//				String genre = rs.getString("genre");
//				String director = rs.getString("director");
//				String actor = rs.getString("actor");
//				String story = rs.getString("story");
//				String img = rs.getString("img");
//				return new MovieInfo(code,genre,director,actor,story,img);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		return null;	
//	}
//}
