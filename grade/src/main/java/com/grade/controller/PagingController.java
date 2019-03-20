package com.grade.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grade.service.BoardService;

/**
 * Servlet implementation class PagingController
 */
public class PagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingController() {
        super();
        try {
			boardService = new BoardService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ininin?????????????????");
		String currentIndex = request.getParameter("startNum");
		List pagingBoards = boardService.getPagingPage(currentIndex);
		String json = boardService.setPaging();
		
		System.out.println(json);
		
		request.setAttribute("paging", json);
		request.setAttribute("boards", pagingBoards);
		request.getRequestDispatcher("/WEB-INF/view/board.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
