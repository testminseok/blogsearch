package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.dto.Blogs;
import com.software.blog.search.openapi.response.NaverOpenApiResponse;
import com.software.blog.search.openapi.response.OpenApiResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
            public Optional<OpenApiResponse> search(String query, String sort, int page, int size) {
                String url = "https://openapi.naver.com/v1/search/blog?query={query}&sort={sort}&display={display}&start={start}";
                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("X-Naver-Client-Id", System.getenv("NAVER_API_CLIENT_ID"));
                    headers.set("X-Naver-Client-Secret", System.getenv("NAVER_API_CLIENT_SECRET"));

                    HttpEntity request = new HttpEntity(headers);
                    RestTemplate restTemplate = new RestTemplate();
                    return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.GET, request, NaverOpenApiResponse.class, query, sort, page, size)
                            .getBody());
                }  catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        Optional<OpenApiResponse> response = naverService.search(query, sort, page, size);
        OpenApiResponse openApiResponse = response.orElse(null);

        Assertions.assertThat(openApiResponse).isNotNull();

        Blogs blogList = openApiResponse.getBlogs();

        Assertions.assertThat(blogList).isNotNull();
        Assertions.assertThat(blogList.isEmpty()).isFalse();
    }

    @Test
    void name() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println(LocalDateTime.parse("2023-03-16T17:35:59.000+09:00", dateTimeFormatter));
    }
}