package com.software.blog.search.word.service.impl;

import com.software.blog.search.word.entity.SearchWord;
import com.software.blog.search.word.service.SearchWordService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Supplier;

@Service("searchWordService")
public class DefaultSearchWordService implements SearchWordService {

    @Override
    public Set<SearchWord> searchWords(Supplier<Set<SearchWord>> function) {
        return function.get();
    }
}
