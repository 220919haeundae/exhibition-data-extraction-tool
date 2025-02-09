package com.kh.preparationtools.model.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kh.preparationtools.model.dto.JsonDto;

@Mapper
public interface TextMapper {

		public int insertExhibition(JsonDto json);
		public int insertExhibitionDetail(JsonDto json);
		public int insertExMap(JsonDto json);
		
		
	    @Select("SELECT TITLE, EX_DATE, MAIN_IMG FROM EXHIBITION")
	    @Results({
	        @Result(property = "mainImg", column = "MAIN_IMG"),
	        @Result(property = "date", column = "EX_DATE"),
	        @Result(property = "title", column = "TITLE")
	    })
	    List<JsonDto> getFilterList();
	
}
