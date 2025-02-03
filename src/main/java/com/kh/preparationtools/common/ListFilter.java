package com.kh.preparationtools.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kh.preparationtools.model.dto.JsonDto;

public class ListFilter {
	
	public ArrayList<JsonDto> filter(List<JsonDto> list) {
		
		ArrayList<JsonDto> filteringResult = new ArrayList<>();
        
        Set<String> filter = new HashSet<>();
        filter.add("축제-문화/예술");
        //filter.add("교육/체험");
        filter.add("축제-자연/경관");
        filter.add("축제-기타");
        filter.add("축제-전통/역사");
        filter.add("축제-시민화합");
        filter.add("전시/미술");
        //filter.add("기타");
        
        Iterator<JsonDto> iterator = list.iterator();
        while(iterator.hasNext()) {
        	JsonDto info = iterator.next();
        	if(filter.contains(info.getCodename())) {
        		filteringResult.add(info);
        	}
        }
	
        return filteringResult;
	}

}
