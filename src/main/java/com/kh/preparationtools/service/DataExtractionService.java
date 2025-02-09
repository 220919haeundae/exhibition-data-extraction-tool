package com.kh.preparationtools.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.preparationtools.common.FilterService;
import com.kh.preparationtools.model.dto.JsonDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataExtractionService {
	
	private final JsonToQueryService jService;
    
	List<JsonDto> list = new ArrayList<JsonDto>();
    
    private final RestAPI api;
    
    private final FilterService filter;
    
    //@Scheduled(fixedDelay = 1000)
    @Scheduled(cron = "0 0 2 * * ?")
    public void replaceJsontoQuery() {
    	int startIndex = 1;
    	int endIndex = 100;
    	
    	filter.initFilter();
    	
    	for(int i=0; i < 50; i++) {
    		try {
        		String data = api.cultureAPI(String.valueOf(startIndex)
        									, String.valueOf(endIndex));
        		
        		list = jService.originExhibitionList(data);
        		
                list = filter.categoryFilter(list);
                
                filter.existDataFilter(list);
            
            	jService.processListData(list);
            	
                System.out.println("json 데이터 : " + list.size());
                
            } catch (Exception e) {
                e.printStackTrace();
                filter.initFilter();
            }
    		startIndex += 100;
            endIndex += 100;
    	}
 
        filter.removeFilter();
    }
}
