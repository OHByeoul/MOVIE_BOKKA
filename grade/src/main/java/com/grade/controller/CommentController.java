package com.grade.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grade.dto.Comment;
import com.grade.service.CommentService;
import com.grade.util.JsonMaker;

/**
 * Servlet implementation class CommentController
 */
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      CommentService commentService; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        commentService = new CommentService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//commentService.addComment();
		String param = request.getParameter("jsonData");
//		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		
//		List list = new ArrayList();
//		list.add("가나다");
//		list.add("sdf");
		
		JsonMaker json = new JsonMaker();
	
		Comment com = json.getGson().fromJson(param, Comment.class);
		
		System.out.println(com.getId());
		System.out.println(com.getContent());
		String json1 = json.getGson().toJson(com);
		System.out.println("toJson");
		System.out.println(json1);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json1);
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
