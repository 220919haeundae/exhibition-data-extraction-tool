package com.kh.preparationtools.service;

import org.springframework.stereotype.Service;

import com.kh.preparationtools.model.dto.JsonDto;
import com.kh.preparationtools.model.mapper.TextMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JsonToQueryService {

	private final TextMapper mapper;
	
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
	
}
