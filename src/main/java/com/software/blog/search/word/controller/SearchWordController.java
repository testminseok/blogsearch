package com.software.blog.search.word.controller;

import com.software.blog.search.common.response.ApiResponse;
import com.software.blog.search.word.entity.SearchWord;
import com.software.blog.search.word.repository.SearchWordRepository;
import com.software.blog.search.word.service.SearchWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class SearchWordController {

    private final SearchWordService searchWordService;
    private final SearchWordRepository searchWordRepository;

    @GetMapping("/popular")
    public ApiResponse<Set<SearchWord>> popularSearchWords() {
        return ApiResponse.toOkResponseEntity(searchWordService.searchWords(searchWordRepository::findTop10ByOrderBySearchCountDesc));
    }
}
