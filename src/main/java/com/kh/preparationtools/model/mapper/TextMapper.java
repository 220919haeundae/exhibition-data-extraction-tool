package com.kh.preparationtools.model.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.kh.preparationtools.model.dto.JsonDto;

@Mapper
public interface TextMapper {

		public int insertExhibition(JsonDto json);
		public int insertExhibitionDetail(JsonDto json);
		public int insertExMap(JsonDto json);
	
}
