package com.software.blog.search.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Blogs {
    private int total;

    private List<Blog> blogs;

    public boolean isEmpty() {
        return blogs.isEmpty();
    }
}
