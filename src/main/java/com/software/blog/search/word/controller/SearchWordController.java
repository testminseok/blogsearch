package com.software.blog.search.word.controller;

import com.software.blog.search.word.repository.SearchWordRepository;
import com.software.blog.search.word.service.SearchWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchWordController {

    private final SearchWordService searchWordService;
    private final SearchWordRepository searchWordRepository;

    @GetMapping("/popular")
    public Object popularSearchWords() {
        return searchWordService.searchWords(searchWordRepository::findTop10ByOrderBySearchCountDesc);
    }
}
