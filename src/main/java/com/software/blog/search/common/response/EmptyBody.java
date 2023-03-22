package com.software.blog.search.common.response;

public class EmptyBody {
    private static final EmptyBody instance = new EmptyBody();

    private EmptyBody() {
    }

    public static EmptyBody getInstance() {
        return instance;
    }
}
