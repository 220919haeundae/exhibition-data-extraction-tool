package com.kh.preparationtools.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class JsonDto {
    private Integer exhibitionNo;

    @SerializedName("ORG_NAME")
    private String org_name; // 주최 기관 이름

    @SerializedName("USE_FEE")
    private String use_fee; // 사용 요금

    @SerializedName("PLAYER")
    private String player; // 공연자 또는 아티스트 이름

    @SerializedName("ORG_LINK")
    private String org_link; // 주최 링크

    @SerializedName("GUNAME")
    private String guname; // 구 이름

    @SerializedName("MAIN_IMG")
    private String main_img; // 메인 이미지 URL

    @SerializedName("THEMECODE")
    private String themecode; // 테마 코드

    @SerializedName("DATE")
    private String date; // 행사 날짜

    @SerializedName("ETC_DESC")
    private String etc_desc; // 기타 설명

    @SerializedName("END_DATE")
    private String end_date; // 종료 날짜

    @SerializedName("TITLE")
    private String title; // 제목

    @SerializedName("TICKET")
    private String ticket; // 티켓 정보

    @SerializedName("CODENAME")
    private String codename; // 코드명 (예: 전시/미술)

    @SerializedName("USE_TRGT")
    private String use_trgt; // 대상

    @SerializedName("PROGRAM")
    private String program; // 프로그램 정보

    @SerializedName("LOT")
    private String lot; // 경도 (위치 정보)

    @SerializedName("LAT")
    private String lat; // 위도 (위치 정보)

    @SerializedName("RGSTDATE")
    private String rgst_date; // 등록 날짜

    @SerializedName("STRTDATE")
    private String strtdate; // 시작 날짜

    @SerializedName("PLACE")
    private String place; // 장소

    @SerializedName("HMPG_ADDR")
    private String hmpg_addr; // 홈페이지 주소

    @SerializedName("IS_FREE")
    private String is_free; // 무료/유료 여부

    private String detail_img_url; // 추가 필드 (JSON에 없음)
}
