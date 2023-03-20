package com.software.blog.search.word.repository;

import com.software.blog.search.word.entity.SearchWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {

    Set<SearchWord> findTop10ByOrderBySearchCountDesc();
}
