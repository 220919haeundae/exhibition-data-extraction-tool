package com.kh.preparationtools.common;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ExtractImg {

	public String imgExtract(String url) {
		
		StringBuilder imgUrl = new StringBuilder();
		
		try {
			Document doc = Jsoup.connect(url).get();

			Elements imgElements = doc.select(".culture-content img");
			
			URL baseUrl = new URL(url);  // 전달받은 URL 객체 생성
			
			for (Element img : imgElements) {
			    String src = img.attr("src");
			    if(src.startsWith("data:")) {
			    	System.out.println("data: 프로토콜 포함된 url");
			    	continue;
			    }
                if (src.startsWith("http")) {
                	// 이미 절대 경로일 경우
                	imgUrl.append((new URL(src)).toString() + " ");
                } else {
                	// 상대 경로를 절대 경로로 변환
                	imgUrl.append((new URL(baseUrl, src)).toString() + " ");
                }
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgUrl.toString().trim();
	}

}
