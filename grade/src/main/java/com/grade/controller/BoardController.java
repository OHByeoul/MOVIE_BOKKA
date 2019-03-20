package com.grade.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grade.dto.Board;
import com.grade.dto.Paging;
import com.grade.service.BoardService;
import com.grade.util.JsonMaker;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService;
	private List<Board> boards;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() throws SQLException {
        super();
        boardService = new BoardService();
        boards = new ArrayList<Board>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boards = boardService.getAllBoards();
		String startNum = request.getParameter("startNum");
		String endNum = request.getParameter("endNum");
		String json = boardService.setPaging();
		boards = boardService.getBoards(startNum,endNum);
		
		System.out.println(json);
		
		request.setAttribute("paging", json);
		request.setAttribute("boards", boards);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/board.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
