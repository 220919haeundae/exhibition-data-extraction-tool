package com.kh.preparationtools.model.dto;

import lombok.Data;

@Data
public class JsonDto {
	private Integer exhibitionNo;
    private String org_name;         // 주최 기관 이름
    private String use_fee;          // 사용 요금
    private String player;          // 공연자 또는 아티스트 이름
    private String org_link;         // 주최 링크
    private String guname;          // 구 이름
    private String main_img;         // 메인 이미지 URL
    private String themecode;       // 테마 코드
    private String date;            // 행사 날짜
    private String etc_desc;         // 기타 설명
    private String end_date;           // 종료 날짜 (epoch time)
    private String title;           // 제목
    private String ticket;          // 티켓 정보
    private String codename;        // 코드명 (예: 전시/미술)
    private String use_trgt;         // 대상
    private String program;         // 프로그램 정보
    private String lot;             // 경도 (위치 정보)
    private String lat;             // 위도 (위치 정보)
    private String rgst_date;        // 등록 날짜
    private String strtdate;          // 시작 날짜 (epoch time)
    private String place;           // 장소
    private String hmpg_addr;        // 홈페이지 주소
    private String is_free;          // 무료/유료 여부
    private String detail_img_url;
	
}
