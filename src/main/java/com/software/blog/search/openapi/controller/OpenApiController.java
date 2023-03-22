package com.software.blog.search.openapi.controller;

import com.software.blog.search.openapi.contants.SortType;
import com.software.blog.search.openapi.dto.Blogs;
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

    @Resource(name = "naverOpenApiService")
    private final OpenApiService naverOpenApiService;

    private final SearchWordService searchWordService;

    private final SearchWordRepository searchWordRepository;

    @GetMapping("/search")
    public Blogs search(String query, String sort, int page, int size) {
        SortType sortType = SortType.of(sort);
        // 검색어 검색횟수 증가
        searchWordService.findByWord(() -> searchWordRepository.findByWord(query))
                .ifPresentOrElse(
                        searchWord -> {
                            synchronized (searchWordRepository) {
                                searchWordRepository.save(searchWord.increaseSearchCount());
                            }
                        },
                        () -> {
                            synchronized (searchWordRepository) {
                                searchWordRepository.save(new SearchWord(query));
                            }
                        });

        return kakaoOpenApiService.search(query, sortType.getKakaoSortType(), page, size)
                .orElseGet(() -> naverOpenApiService.search(query, sortType.getNaverSortType(), page, size).orElseThrow(RuntimeException::new)).getBlogs();
    }
}
