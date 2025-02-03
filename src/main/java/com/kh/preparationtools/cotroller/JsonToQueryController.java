package com.kh.preparationtools.cotroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.preparationtools.common.ExtractImg;
import com.kh.preparationtools.common.ListFilter;
import com.kh.preparationtools.model.dto.JsonDto;
import com.kh.preparationtools.service.JsonToQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JsonToQueryController {

    
	private static int count = 0;
    ArrayList<JsonDto> list = new ArrayList<JsonDto>();
    StringBuilder jsonString = new StringBuilder();
    StringBuilder queryString = new StringBuilder();
    String com = "\', ";
    String target = "/static/Json.txt";
    private final JsonToQueryService jService;
    private final ExtractImg extimg;
    private String regex = "(\\d{1,3}(?:,\\d{3})*)\\s?원|(\\\\d+)만원";
    
    @GetMapping("/jsontoquery")
    public String replaceJsontoQuery(Model model) {
    	Resource resource = new ClassPathResource("static/Json.txt");
    	Pattern pattern = Pattern.compile(regex);
    	
        try {
        	
        	if(count == 0) {
        	
            	File file = resource.getFile();  // 파일 객체로 접근할 수 있습니다.
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\workspace/insertquery.txt")));
                String readLine;
                // 파일에서 한 줄씩 읽어서 jsonString에 추가
                while ((readLine = br.readLine()) != null) {
                    jsonString.append(readLine);
                }
                br.close();

                JSONObject jsonObject = new JSONObject(jsonString.toString());

                JSONArray dataArray = jsonObject.getJSONArray("DATA");

                String dataArrayString = dataArray.toString();

                Gson gson = new Gson();
                list = gson.fromJson(dataArrayString, new TypeToken<ArrayList<JsonDto>>() {}.getType());
                
                list = new ListFilter().filter(list);
        		
        	}
            
            for(int i = count; i < list.size(); i++) {
            	count += 1;
            	log.info("{}번째 데이터 =====> {}", i+1, list.get(i).getHmpg_addr());
            	list.get(i).setDetail_img_url(extimg.imgExtract(list.get(i).getHmpg_addr()));
            	JsonDto data = list.get(i);
            	String useFee = data.getUse_fee();
            	if(useFee != null) {
            		Matcher matcher = pattern.matcher(useFee);
                	String result = "";
                	while(matcher.find()) {
                		if(matcher.group(1) != null) {
                			result += matcher.group(1)+"/";
                		} else if(matcher.group(2) != null) {
                			result += Integer.parseInt(matcher.group(2)) * 10000;
                		}
                	}
                	
                	if(result.equals("") && data.getIs_free().equals("유료")) {
                		data.setUse_fee("홈페이지 참조");
                	} else {
                		String[] tempStr = result.split("/");
                		if(tempStr.length > 1) {
                			data.setUse_fee("홈페이지 참조");
                		} else {
                			data.setUse_fee(tempStr[0]);
                		}
                		
                	}
                	
            	}
            	
            	
            	
            	
            	jService.jsonToQuery(data);
            }
            
            System.out.println("json 데이터 : " + list.size());
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "";
    }

}