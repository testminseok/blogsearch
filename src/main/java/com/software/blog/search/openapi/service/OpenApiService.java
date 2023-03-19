package com.software.blog.search.openapi.service;

import com.software.blog.search.openapi.response.OpenApiResponse;

/**
 * 블로그 검색 OpenApi 서비스
 * */
public interface OpenApiService {
    OpenApiResponse search(String query, String sort, int page, int size);

}
