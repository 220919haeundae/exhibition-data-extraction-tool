package com.kh.preparationtools.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class JsonDto {
    private Integer exhibitionNo;

    @SerializedName("ORG_NAME")
    private String orgName; // 주최 기관 이름

    @SerializedName("USE_FEE")
    private String useFee; // 사용 요금

    @SerializedName("PLAYER")
    private String player; // 공연자 또는 아티스트 이름

    @SerializedName("ORG_LINK")
    private String orgLink; // 주최 링크

    @SerializedName("GUNAME")
    private String guname; // 구 이름

    @SerializedName("MAIN_IMG")
    private String mainImg; // 메인 이미지 URL

    @SerializedName("THEMECODE")
    private String themeCode; // 테마 코드

    @SerializedName("DATE")
    private String date; // 행사 날짜

    @SerializedName("ETC_DESC")
    private String etcDesc; // 기타 설명

    @SerializedName("END_DATE")
    private String endDate; // 종료 날짜

    @SerializedName("TITLE")
    private String title; // 제목

    @SerializedName("TICKET")
    private String ticket; // 티켓 정보

    @SerializedName("CODENAME")
    private String codename; // 코드명 (예: 전시/미술)

    @SerializedName("USE_TRGT")
    private String usetrgt; // 대상

    @SerializedName("PROGRAM")
    private String program; // 프로그램 정보

    @SerializedName("LOT")
    private String lot; // 경도 (위치 정보)

    @SerializedName("LAT")
    private String lat; // 위도 (위치 정보)

    @SerializedName("RGSTDATE")
    private String rgstDate; // 등록 날짜

    @SerializedName("STRTDATE")
    private String strtDate; // 시작 날짜

    @SerializedName("PLACE")
    private String place; // 장소

    @SerializedName("HMPG_ADDR")
    private String hmpgAddr; // 홈페이지 주소

    @SerializedName("IS_FREE")
    private String isFree; // 무료/유료 여부

    private String detailImgUrl; // 추가 필드 (JSON에 없음)
}
