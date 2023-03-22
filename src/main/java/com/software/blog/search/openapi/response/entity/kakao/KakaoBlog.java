package com.software.blog.search.openapi.response.entity.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoBlog {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", url='" + url + '\'' +
                ", blogname='" + blogname + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
