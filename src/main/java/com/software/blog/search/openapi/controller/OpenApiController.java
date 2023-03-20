package com.software.blog.search.openapi.controller;

import com.software.blog.search.openapi.service.OpenApiService;
import com.software.blog.search.word.entity.SearchWord;
import com.software.blog.search.word.repository.SearchWordRepository;
import com.software.blog.search.word.service.SearchWordService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    @Resource(name = "kakaoOpenApiService")
    private final OpenApiService kakaoOpenApiService;

    private final SearchWordService searchWordService;
    private final SearchWordRepository searchWordRepository;

    @GetMapping("/search")
    public Object search(String query, String sort, int page, int size) {

        // 검색어 검색횟수 증가
        searchWordService.findByWord(() -> searchWordRepository.findByWord(query))
                .ifPresentOrElse(
                        searchWord -> searchWordRepository.save(searchWord.increaseSearchCount()),
                        () -> searchWordRepository.save(new SearchWord(query)));

        return kakaoOpenApiService.search(query, sort, page, size);
    }
}
