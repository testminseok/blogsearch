package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.response.KakaoOpenApiResponse;
import com.software.blog.search.openapi.response.OpenApiResponse;
import com.software.blog.search.openapi.response.entity.kakao.Blog;
import com.software.blog.search.openapi.service.OpenApiService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

class KaKaoOpenApiServiceTest {

    @Test
    @DisplayName("카카오 openapi 블로그 조회")
    public void blogSearch() {
        String query = "피자";
        String sort = "accuracy"; // accuracy | recency
        int page = 1;
        int size = 10;

        OpenApiService kakaoService = new KaKaoOpenApiService() {
            @Override
            public Optional<OpenApiResponse> search(String query, String sort, int page, int size) {
                String url = "https://dapi.kakao.com/v2/search/blog?query={query}&sort={sort}&page={page}&size={size}";
                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + System.getenv("KAKAO_API_KEY"));

                    HttpEntity request = new HttpEntity(headers);
                    RestTemplate restTemplate = new RestTemplate();
                    return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.GET, request, KakaoOpenApiResponse.class, query, sort, page, size)
                            .getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        Optional<OpenApiResponse> response = kakaoService.search(query, sort, page, size);
        OpenApiResponse openApiResponse = response.orElse(null);

        Assertions.assertThat(openApiResponse).isNotNull();

        List<Blog> blogList = openApiResponse.getDataBody();

        Assertions.assertThat(blogList).isNotNull();
        Assertions.assertThat(blogList.size()).isGreaterThan(0);
    }
}