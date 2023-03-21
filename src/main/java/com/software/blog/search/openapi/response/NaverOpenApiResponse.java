package com.software.blog.search.openapi.response;

import com.software.blog.search.openapi.response.entity.naver.Blog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NaverOpenApiResponse implements OpenApiResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Blog> items;

    @Override
    public List<Blog> getDataBody() {
        return items;
    }
}
