package com.grade.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grade.dto.Board;
import com.grade.service.BoardService;

/**
 * Servlet implementation class DetailController
 */
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BoardService boardService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
        super();
        try {
			boardService = new BoardService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Board board = boardService.getDetailBoard(id);
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/view/detailBoard.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
