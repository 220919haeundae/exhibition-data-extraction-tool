package com.kh.preparationtools.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kh.preparationtools.model.dto.JsonDto;
import com.kh.preparationtools.model.mapper.TextMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FilterService {
	
	private HashMap<String, String> filterMap = null;
	
	Set<String> filterSet = null;
	
	private final TextMapper mapper;
	
	public ArrayList<JsonDto> categoryFilter(List<JsonDto> list) {
		
		ArrayList<JsonDto> filteringResult = new ArrayList<>();
        
        Iterator<JsonDto> iterator = list.iterator();
        while(iterator.hasNext()) {
        	JsonDto info = iterator.next();
        	if(filterSet.contains(info.getCodename())) {
        		filteringResult.add(info);
        	}
        }
	
        return filteringResult;
	}
	
	public void initFilter() {
		this.filterMap = new HashMap<>();
		this.filterSet = new HashSet<>();
		
		List<JsonDto> filterList = mapper.getFilterList();
		
		for (JsonDto dto : filterList) {
			
	        filterMap.put(dto.getTitle()+dto.getMainImg(), dto.getDate());
	    }
		filterSet.add("축제-문화/예술");
		filterSet.add("축제-자연/경관");
		filterSet.add("축제-기타");
		filterSet.add("축제-전통/역사");
		filterSet.add("축제-시민화합");
		filterSet.add("전시/미술");
	}

	public void removeFilter() {
		this.filterMap = null;
		this.filterSet = null;
	}
	
	
	public void existDataFilter(List<JsonDto> list) {
	    
	    Iterator<JsonDto> iterator = list.iterator();
	    while (iterator.hasNext()) {
	    	
	        JsonDto dto = iterator.next();
	        
	        String key = dto.getTitle() + dto.getMainImg();
	        
	        if(filterMap.get(key) != null) {
	        	
	        	if(filterMap.get(key).equals(dto.getDate())) {
	        		
	        		iterator.remove();
	        	};
	        }
	    }
	}
}
