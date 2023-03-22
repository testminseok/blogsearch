package com.software.blog.search.openapi.response;

import com.software.blog.search.openapi.dto.Blog;
import com.software.blog.search.openapi.dto.Blogs;
import com.software.blog.search.openapi.response.entity.kakao.KakaoBlog;
import com.software.blog.search.openapi.response.entity.kakao.KakaoMeta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoOpenApiResponse implements OpenApiResponse {

    private KakaoMeta meta;
    private List<KakaoBlog> documents;

    @Override
    public Blogs getBlogs() {
        List<Blog> blogs = documents.stream().map(item -> {
            Blog blog = new Blog();
            blog.setTitle(item.getTitle());
            blog.setSummary(item.getContents());
            blog.setUrl(item.getUrl());
            blog.setBlogname(item.getBlogname());
            blog.setDatetime(item.getDatetime());
            return blog;
        }).toList();
        return new Blogs(meta.getTotal_count(), blogs);
    }
}
