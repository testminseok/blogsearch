package com.software.blog.search.openapi.response;

import com.software.blog.search.openapi.response.entity.kakao.Blog;
import com.software.blog.search.openapi.response.entity.kakao.Meta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoOpenApiResponse implements OpenApiResponse {

    private Meta meta;
    private List<Blog> documents;

    @Override
    public OpenApiResponseHeader getDataHeader() {
        return null;
    }

    @Override
    public List<Blog> getDataBody() {
        return documents;
    }
}
