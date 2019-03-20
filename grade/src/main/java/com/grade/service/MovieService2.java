//package com.grade.service;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import com.grade.dao.MovieDAO2;
//import com.grade.dto.MovieInfo;
//import com.grade.util.Crawling;
//import com.grade.util.MovieInfoRequest;
//
//public class MovieService2 {
//	private MovieDAO2 movieDAO;
//	private Crawling crawling;
//	private MovieInfo movieInfo;
//	private MovieInfoRequest movieInfoRequest;
//	//private List<MovieInfo> movieInfolists;
//
//	public MovieService2() {
//		try {
//			movieDAO = new MovieDAO2();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		crawling = new Crawling();
//		movieInfo = new MovieInfo();
//		movieInfoRequest = new MovieInfoRequest();
//	//	movieInfolists = new ArrayList<MovieInfo>();
//	}
//
//	public void findMovie(String movieName) {
//		String url;
//		String search = Crawling.urlByFindName;
//		String searchDetail = Crawling.urlByFindName + Crawling.urlByDetailName;
//		crawling.setMovieName(movieName);
//		url = Crawling.URL + Crawling.PRESEARCH_URL + crawling.getMovieName() + Crawling.POSTURL;
//		crawling.createDocument(url);
//		crawling.createElements(search);
//
//		Elements elements = crawling.getElements();
//		if (elements.size() >= 2) {
//			elements.remove(1);
//		}
//	}
//
//	public List getMovieDetailName() {
//		List movieNames = new ArrayList<String>();
//		Elements elements = crawling.getElements();
//		for (Element element : elements) {
//			movieNames.add(element.toString());
//		}
//		return movieNames;
//	}
//
//	public MovieInfo findDetailInfo(String clickedCode) {
//		String url = Crawling.URL + clickedCode;
//		String searchPoster = ".poster a img";
//		String searchGenre = ".info_spec dd p span";
//		String searchStory = ".story_area p";
//		String searchInfo = ".info_spec dd";
//		String searchScore = ".star_score a em";
//		//movieInfo.setCode(clickedCode);
//		crawling.createDocument(url);
//		crawling.createElements(searchInfo);
//		Elements elements = crawling.getElements();
//		setMovieInfo(elements);
//		crawling.createElements(searchStory);
//		elements = crawling.getElements();
//		setMovieStory(elements);
//		System.out.println(movieInfo.toString());
//		crawling.createElements(searchPoster);
//		elements = crawling.getElements();
//		String img = elements.get(0).attr("src").toString();
//	//	movieInfo.setImg(img);
//		if(movieDAO.getMovieInfo(clickedCode)==null) {
//			movieDAO.InsertMovieInfo(movieInfo);
//		} 
//		return movieDAO.getMovieInfo(clickedCode);
//		//////////////////////
//	}
//
//	private void setMovieStory(Elements elements) {
//		String story = crawling.htmlTotext(elements.toString());
////		movieInfo.setStory(story);
//	}
//
//	private void setMovieInfo(Elements elements) {
////		String genre = elements.get(0).getElementsByTag("span").get(0).toString();
////		String infos = "";
////		String director = "";
////		String actor = "";
////		
////		for (int j = 1; j<elements.size()-1; j++) {
////			for (int i = 0; i < elements.get(j).childNodes().size(); i++) {
////				infos += elements.get(j).getElementsByTag("a").get(i).text();
////				if(i<elements.get(j).childNodes().size()-1) {
////					infos += ",";
////				}
////			}
////			if(j==1) {
////				director = infos;
////			} else if(j==2) {
////				actor = infos;
////			}
////			infos = "";
////		}
////		genre = crawling.htmlTotext(genre);
////		movieInfo.setGenre(genre);
////		movieInfo.setDirector(director);
////		movieInfo.setActor(actor);
//	}
//
//	public String getMovieDetailInfo(String movieName) {
//		return movieInfoRequest.getRequestURL(movieName); 
//	}
//	
//	public int InsertMovieInfo(MovieInfo genre) {
//		gen
//		
//	}
//}