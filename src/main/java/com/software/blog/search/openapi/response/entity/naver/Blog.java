package com.software.blog.search.openapi.response.entity.naver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Blog {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", bloggername='" + bloggername + '\'' +
                ", bloggerlink='" + bloggerlink + '\'' +
                ", postdate='" + postdate + '\'' +
                '}';
    }
}
