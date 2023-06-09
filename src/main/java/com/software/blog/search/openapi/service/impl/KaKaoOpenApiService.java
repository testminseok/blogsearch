package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.response.KakaoOpenApiResponse;
import com.software.blog.search.openapi.response.OpenApiResponse;
import com.software.blog.search.openapi.service.OpenApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service("kakaoOpenApiService")
public class KaKaoOpenApiService implements OpenApiService {

    @Value("${openapi.kakao.api.key}")
    private String API_KEY;

    @Override
    public Optional<OpenApiResponse> search(String query, String sort, int page, int size) {
        Assert.hasText(query, "query must not be empty");
        Assert.hasText(sort, "sort must not be empty");

        String url = "https://dapi.kakao.com/v2/search/blog?query={query}&sort={sort}&page={page}&size={size}";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + API_KEY);

            HttpEntity request = new HttpEntity(headers);
            RestTemplate restTemplate = new RestTemplate();
            return Optional.ofNullable(
                    restTemplate.exchange(url,
                                    HttpMethod.GET,
                                    request,
                                    KakaoOpenApiResponse.class,
                                    query,
                                    sort,
                                    page,
                                    size)
                            .getBody());
        } catch (Exception e) {
            log.error("KaKaoOpenApiService.search() error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
