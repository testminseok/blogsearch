package com.software.blog.search.openapi.response;

import com.software.blog.search.openapi.dto.Blog;
import com.software.blog.search.openapi.dto.Blogs;
import com.software.blog.search.openapi.response.entity.naver.NaverBlog;
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
    private List<NaverBlog> items;

    @Override
    public Blogs getBlogs() {
        List<Blog> blogs = items.stream().map(item -> {
            Blog blog = new Blog();
            blog.setTitle(item.getTitle());
            blog.setSummary(item.getDescription());
            blog.setUrl(item.getLink());
            blog.setBlogname(item.getBloggername());
            blog.setDatetime(item.getPostdate());
            return blog;
        }).toList();
        return new Blogs(total, blogs);
    }
}
