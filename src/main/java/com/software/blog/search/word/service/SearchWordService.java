package com.software.blog.search.word.service;

import com.software.blog.search.word.entity.SearchWord;

import java.util.Set;
import java.util.function.Supplier;

public interface SearchWordService {

    Set<SearchWord> searchWords(Supplier<Set<SearchWord>> function);
}
