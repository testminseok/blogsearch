package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.response.NaverOpenApiResponse;
import com.software.blog.search.openapi.response.OpenApiResponse;
import com.software.blog.search.openapi.response.entity.naver.Blog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

class NaverOpenApiServiceTest {

    @Test
    @DisplayName("네이버 openapi 블로그 조회")
    public void blogSearch() {
        String query = "피자";
        String sort = "sim"; // sim | date
        int page = 1;
        int size = 10;

        NaverOpenApiService naverService = new NaverOpenApiService() {
            @Override
            public OpenApiResponse search(String query, String sort, int page, int size) {
                String url = "https://openapi.naver.com/v1/search/blog?query={query}&sort={sort}&display={display}&start={start}";
                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("X-Naver-Client-Id", System.getenv("NAVER_API_CLIENT_ID"));
                    headers.set("X-Naver-Client-Secret", System.getenv("NAVER_API_CLIENT_SECRET"));

                    HttpEntity request = new HttpEntity(headers);
                    RestTemplate restTemplate = new RestTemplate();
                    return restTemplate.exchange(url, HttpMethod.GET, request, NaverOpenApiResponse.class, query, sort, page, size)
                            .getBody();
                }  catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        OpenApiResponse response = naverService.search(query, sort, page, size);

        Assertions.assertThat(response).isNotNull();

        List<Blog> blogList = response.getDataBody();

        Assertions.assertThat(blogList).isNotNull();
        Assertions.assertThat(blogList.size()).isGreaterThan(0);
    }
}