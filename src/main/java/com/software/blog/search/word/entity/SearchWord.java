package com.software.blog.search.word.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity(name = "search_word")
public class SearchWord {

    @Id
    private Long id;

    @Column(unique = true)
    private String word;

    private int searchCount;

    public SearchWord() {}

    public SearchWord(String query) {
        this(0L, query, 1);
    }

    public SearchWord(long id, String word, int searchCount) {
        this.id = id;
        this.word = word;
        this.searchCount = searchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchWord that = (SearchWord) o;
        return word.equals(that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return "SearchWord{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", searchCount=" + searchCount +
                '}';
    }

    public SearchWord increaseSearchCount() {
        return new SearchWord(id, word, searchCount + 1);
    }
}
