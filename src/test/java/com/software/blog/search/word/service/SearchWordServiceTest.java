package com.software.blog.search.word.service;

import com.software.blog.search.word.entity.SearchWord;
import com.software.blog.search.word.service.impl.DefaultSearchWordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

class SearchWordServiceTest {
    private Set<SearchWord> searchWords =
            Set.of(new SearchWord(0, "피자", 1),
                    new SearchWord(1, "치킨", 2),
                    new SearchWord(2, "햄버거", 3),
                    new SearchWord(3, "족발", 4),
                    new SearchWord(4, "짜장면", 5),
                    new SearchWord(5, "짬뽕", 6),
                    new SearchWord(6, "탕수육", 7),
                    new SearchWord(7, "국밥", 8),
                    new SearchWord(8, "초밥", 9),
                    new SearchWord(9, "덮밥", 10),
                    new SearchWord(10, "돈까스", 11),
                    new SearchWord(11, "라멘", 12));
    private SearchWordService searchWordService;

    @BeforeEach
    void setUp() {
        searchWordService = new DefaultSearchWordService();
    }

    @Test
    @DisplayName("인기 검색어 상위 10개를 조회한다.")
    void tenPopularSearchWords() {
        int size = 10;
        Set<SearchWord> popularSearchWords = searchWordService.searchWords(() ->
                searchWords.stream()
                        .sorted((o1, o2) -> o2.getSearchCount() - o1.getSearchCount())
                        .limit(size)
                        .collect(Collectors.toSet()));

        Assertions.assertNotNull(popularSearchWords);
        Assertions.assertTrue(popularSearchWords.size() == 10);
    }
}