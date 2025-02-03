package com.kh.preparationtools.common;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Snippet {

	public static void main(String[] args) {
		
		imgExtract("https://culture.seoul.go.kr/culture/culture/cultureEvent/view.do?cultcode=150284&menuNo=200013");
		
	}
	
	public static String imgExtract(String url) {
		
		StringBuilder imgUrl = new StringBuilder();
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements imgElements = doc.select(".culture-content img");
			
			URL baseUrl = new URL(url);  // 전달받은 URL 객체 생성
			
			for (Element img : imgElements) {
				URL absoluteUrl = null;
			    String src = img.attr("src");
		    	absoluteUrl = new URL(baseUrl, src);  // 상대 경로로부터 절대 경로 생성
		    	imgUrl.append(absoluteUrl.toString() + " ");
			    System.out.println("absoluteUrl ::::: " + absoluteUrl);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgUrl.toString().trim();
	}

}
