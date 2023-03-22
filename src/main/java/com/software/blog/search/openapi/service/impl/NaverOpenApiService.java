package com.software.blog.search.openapi.service.impl;

import com.software.blog.search.openapi.response.NaverOpenApiResponse;
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
@Service("naverOpenApiService")
public class NaverOpenApiService implements OpenApiService {

    @Value("${openapi.naver.api.client-id}")
    private String CLIENT_ID;

    @Value("${openapi.naver.api.client-secret}")
    private String CLIENT_SECRET;

    @Override
    public Optional<OpenApiResponse> search(String query, String sort, int page, int size) {
        Assert.hasText(query, "query must not be empty");
        Assert.hasText(sort, "sort must not be empty");

        String url = "https://openapi.naver.com/v1/search/blog?query={query}&sort={sort}&start={start}&display={display}";
        try {
            int start = (page - 1) * size + 1;
            int display = size;

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", CLIENT_ID);
            headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

            HttpEntity request = new HttpEntity(headers);
            RestTemplate restTemplate = new RestTemplate();
            return Optional.ofNullable(
                    restTemplate.exchange(
                                    url,
                                    HttpMethod.GET,
                                    request,
                                    NaverOpenApiResponse.class,
                                    query,
                                    sort,
                                    start,
                                    display)
                            .getBody());
        }  catch (Exception e) {
            log.error("NaverOpenApiService.search() error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
