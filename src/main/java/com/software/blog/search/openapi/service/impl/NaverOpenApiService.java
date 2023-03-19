package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.response.NaverOpenApiResponse;
import com.software.blog.search.openapi.response.OpenApiResponse;
import com.software.blog.search.openapi.service.OpenApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("naverOpenApiService")
public class NaverOpenApiService implements OpenApiService {

    @Value("${openapi.naver.api.client-id}")
    private String CLIENT_ID;

    @Value("${openapi.naver.api.client-secret}")
    private String CLIENT_SECRET;

    @Override
    public OpenApiResponse search(String query, String sort, int page, int size) {
        String url = "https://openapi.naver.com/v1/search/blog?query={query}&sort={sort}&display={display}&start={start}";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", CLIENT_ID);
            headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

            HttpEntity request = new HttpEntity(headers);
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(url, HttpMethod.GET, request, NaverOpenApiResponse.class, query, sort, page, size)
                    .getBody();
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
