package com.kh.preparationtools.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.preparationtools.common.ExtractImg;
import com.kh.preparationtools.common.FilterService;
import com.kh.preparationtools.model.dto.JsonDto;
import com.kh.preparationtools.model.mapper.TextMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonToQueryService {

    private String regex = "(\\d{1,3}(?:,\\d{3})*)\\s?원|(\\\\d+)만원";
    
    Pattern pattern = Pattern.compile(regex);
    
	ArrayList<JsonDto> list = new ArrayList<JsonDto>();
	
	private final ExtractImg extimg;
	
	private final TextMapper mapper;
	
	private static int count;
	
	public int jsonToQuery(JsonDto jd) {
		int result = 0;
		result = mapper.insertExhibition(jd);
		result = mapper.insertExhibitionDetail(jd);
		result = mapper.insertExMap(jd);
		
		return result;
	}
	
	
	public String generateUseFee() {
		int randInt = (int)(Math.random()*19) + 1;
		
		return String.valueOf(5000*randInt);
	}
	
	
	/**
	 * @param str RestAPI에서 받아온 json 문자열
	 * @return 전시/행사 정보 관련 json 문자열을 가공 및 1차 필터링 후 반환하는 DTO 리스트
	 */
	public List<JsonDto> originExhibitionList(String str) {

	    JSONObject jsonObject = new JSONObject(str);

	    JSONObject culturalEventInfo = jsonObject.getJSONObject("culturalEventInfo");

	    JSONArray row = culturalEventInfo.getJSONArray("row");

	    String strrow = row.toString();
	    
	    Gson gson = new Gson();
	    
	    list = gson.fromJson(strrow, new TypeToken<ArrayList<JsonDto>>() {}.getType());
	    
	    return list;
	}
	
    private String parseUseFee(String useFee, String isFree) {
    	
        Matcher matcher = pattern.matcher(useFee);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                result.append(matcher.group(1)).append("/");
            } else if (matcher.group(2) != null) {
                result.append(Integer.parseInt(matcher.group(2)) * 10000);
            }
        }

        if (result.length() == 0 && "유료".equals(isFree)) {
            return "홈페이지 참조";
        }

        String[] tempStr = result.toString().split("/");
        return (tempStr.length > 1) ? "홈페이지 참조" : tempStr[0];
    }
    
    
    
    public void processListData(List<JsonDto> list) {
        for (int i = count; i < list.size(); i++) {
            JsonDto data = list.get(i);

            log.info("{}번째 데이터 =====> {}", i + 1, data.getHmpg_addr());

            // 상세 이미지 URL 추출
            data.setDetail_img_url(extimg.imgExtract(data.getHmpg_addr()));

            // 이용 요금 정보 처리
            if (data.getUse_fee() != null) {
                data.setUse_fee(parseUseFee(data.getUse_fee(), data.getIs_free()));
            }

            // 데이터 저장
            saveData(data);
        }
    }
    
    private void saveData(JsonDto data) {
        jsonToQuery(data);
    }

	public void initCount() {
		count = 0;
	}

}
