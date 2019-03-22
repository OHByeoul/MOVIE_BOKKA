package com.grade.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grade.dto.MovieInfo;
import com.grade.service.MovieService;
import com.grade.util.JsonMaker;
import com.grade.vo.GetMovieInfoForm;

/**
 * Servlet implementation class CrawlingController
 */
@WebServlet(urlPatterns = { "/movie/searchMovieName", "/movie/getInfo","/movie/getMovieInfo"})
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieService movieService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieController() {
		super();
		movieService = new MovieService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doDispatcher(request, response);
	}

	private void doDispatcher(HttpServletRequest request, HttpServletResponse response) {
		String projectPath = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(projectPath.length(), uri.length());
		System.out.println("path : " + path);
		if (path.equals("/movie/searchMovieName")) {
			try {
				request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if(path.equals("/movie/getInfo")) {
			String movieName = request.getParameter("movieName");
			System.out.println("movieName : "+movieName);
			String json = movieService.getMovieDetailInfo(movieName);
			System.out.println(json+" "+movieName);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(path.equals("/movie/getMovieInfo")) {
			int code = Integer.parseInt(request.getParameter("code"));
			String title = request.getParameter("title");
			String img = request.getParameter("img");
			String userRating = request.getParameter("userRating");			
			MovieInfo movieInfo = new MovieInfo(code,title,img,userRating);
			
			//String genre = request.getParameter("genre");
			//Genre genre = new Genre(code, genre);
			String director = request.getParameter("director");
			String actor = request.getParameter("actor");
			Map<String, String> subInfo = new HashMap<String, String>();
			//subInfo.add(genre);
			subInfo.put("director", director);
			subInfo.put("actor", actor);
			GetMovieInfoForm movieInfoForm = new GetMovieInfoForm();
			movieInfoForm = movieService.getMovieInfo(movieInfo, subInfo);
			try {
				request.setAttribute("movieInfoForm", movieInfoForm);
				request.getRequestDispatcher("/WEB-INF/view/movieDetailInfo.jsp")
				.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}