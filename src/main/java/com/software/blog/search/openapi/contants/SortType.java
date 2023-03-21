package com.software.blog.search.openapi.contants;

public enum SortType {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private String kakaoSortType;
    private String naverSortType;

    SortType(String kakaoSortType, String naverSortType) {
        this.kakaoSortType = kakaoSortType;
        this.naverSortType = naverSortType;
    }

    public static SortType of(String sort) {
        for (SortType sortType : SortType.values()) {
            if (sortType.getKakaoSortType().equals(sort) || sortType.getNaverSortType().equals(sort)) {
                return sortType;
            }
        }
        return null;
    }

    public String getKakaoSortType() {
        return kakaoSortType;
    }

    public String getNaverSortType() {
        return naverSortType;
    }
}
