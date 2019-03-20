package com.grade.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
   public static final String URL="https://movie.naver.com";
   public static final String PRESEARCH_URL ="/movie/search/result.nhn?query=";
   public static final String POSTURL = "&section=all&ie=utf8";
   public static final String urlByFindName = ".search_list_1";
   public static final String urlByDetailName = "> dl > dt > a";
   private String movieName;
   private Document document;
   private Elements elements;

   public Crawling() {
      elements = new Elements();
   }
   
   public Crawling(String movieName) {
      this.movieName = movieName;
   }
   
   public void createDocument(String url){
      try {
         document = Jsoup.connect(url).get();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public Elements createElements(String url) {
      if(!elements.isEmpty()) {
         clearElements();
      }
      elements = getDocument().select(url);       
      return elements;
   }
   
   public Elements addElements(String url) {
      if(elements.isEmpty()) {
         elements = getDocument().select(url);
         System.out.println("first in");
      } else {
         elements.addAll(getDocument().select(url));
         System.out.println("sizee : "+elements.size());
      }
      return elements;
   } 
   
   public String getMovieName() {
      return movieName;
   }
   
   public void setMovieName(String movieName) {
      this.movieName = movieName;
   }
   
   public Document getDocument() {
      return document;
   }
   
   public void setDocument(Document document) {
      this.document = document;
   }
   
   public Elements getElements() {
      return elements;
   }

   public void setElements(Elements elements) {
      this.elements = elements;
   }
   
   public void clearElements() {
      this.elements.clear();
   }
   
   public String htmlTotext(String html) {
      return Jsoup.parse(html).text();
   }
}