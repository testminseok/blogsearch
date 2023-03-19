package com.software.blog.search.openapi.controller;

import com.software.blog.search.openapi.service.OpenApiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiController {

    @Resource(name = "kakaoOpenApiService")
    private OpenApiService kakaoOpenApiService;

    @GetMapping("/search")
    public Object search(String query, String sort, int page, int size) {
        return kakaoOpenApiService.search(query, sort, page, size);
    }
}
