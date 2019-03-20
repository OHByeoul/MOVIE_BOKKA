package com.grade.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.grade.dao.MovieDAO;
import com.grade.dto.Director;
import com.grade.dto.Genre;
import com.grade.dto.MovieInfo;
import com.grade.util.Crawling;
import com.grade.util.MovieInfoRequest;
import com.grade.vo.GetMovieInfoForm;

public class MovieService {
	private MovieDAO movieDAO;
	private Crawling crawling;
	private MovieInfo movieInfo;
	private MovieInfoRequest movieInfoRequest;
	private GetMovieInfoForm getMovieInfoForm;

	public MovieService() {
		movieDAO = new MovieDAO();
		crawling = new Crawling();
		movieInfo = new MovieInfo();
		movieInfoRequest = new MovieInfoRequest();
		getMovieInfoForm = new GetMovieInfoForm();
	}

	public void findMovie(String movieName) {

	}

	public List getMovieDetailName() {
		List movieNames = new ArrayList<String>();
		Elements elements = crawling.getElements();
		for (Element element : elements) {
			movieNames.add(element.toString());
		}
		return movieNames;
	}

	// public MovieInfo findDetailInfo(String clickedCode) {
	// String url = Crawling.URL + clickedCode;
	// String searchPoster = ".poster a img";
	// String searchGenre = ".info_spec dd p span";
	// String searchStory = ".story_area p";
	// String searchInfo = ".info_spec dd";
	// String searchScore = ".star_score a em";
	// //movieInfo.setCode(clickedCode);
	// crawling.createDocument(url);
	// crawling.createElements(searchInfo);
	// Elements elements = crawling.getElements();
	// setMovieInfo(elements);
	// crawling.createElements(searchStory);
	// elements = crawling.getElements();
	// setMovieStory(elements);
	// System.out.println(movieInfo.toString());
	// crawling.createElements(searchPoster);
	// elements = crawling.getElements();
	// String img = elements.get(0).attr("src").toString();
	// // movieInfo.setImg(img);
	// if(movieDAO.getMovieInfo(clickedCode)==null) {
	// movieDAO.InsertMovieInfo(movieInfo);
	// }
	// return movieDAO.getMovieInfo(clickedCode);
	// //////////////////////
	// }

	private void setMovieStory(Elements elements) {
		String story = crawling.htmlTotext(elements.toString());
		// movieInfo.setStory(story);
	}

	private void setMovieInfo(Elements elements) {

	}

	public String getMovieDetailInfo(String movieName) {
		return movieInfoRequest.getRequestURL(movieName);
	}

	// public int InsertMovieInfo(MovieInfo genre) {
	//
	// }

	public GetMovieInfoForm getMovieInfo(MovieInfo movie, Map<String, String> subInfo) {
		int movieCode = movie.getM_code();

		if (!isExist(movieCode)) {
			System.out.println("inin");
			movieDAO.InsertMovieInfo(movie);
			for (Map.Entry<String, String> each : subInfo.entrySet()) {
				String key = each.getKey();
				if (key.equals("director")) {
					String value = each.getValue();
					System.out.println("map val : "+value);
					splitSubInfo(movieCode, key, value);
				} else if (key.equals("actor")) {
					String value = each.getValue();
					splitSubInfo(movieCode, key, value);
				}
			}
		}
		getMovieInfoForm = movieDAO.getMovieInfo(movieCode);
		return getMovieInfoForm;
	}

	private boolean isExist(int movieCode) {
		return movieDAO.isExist(movieCode);
	}

	private void splitSubInfo(int movieCode, String key, String value) {
		String[] temp;
		if (value.contains("|")) {
			temp = value.split("\\|");
			for (int i = 0; i < temp.length; i++) {
				movieDAO.InsertSubInfo(movieCode, key, temp[i]);
			}
		} else {
			movieDAO.InsertSubInfo(movieCode, key, value);
		}
	}
}