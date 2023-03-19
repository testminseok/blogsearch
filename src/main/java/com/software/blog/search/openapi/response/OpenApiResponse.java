package com.software.blog.search.openapi.response;

import java.util.List;

public interface OpenApiResponse {
    OpenApiResponseHeader getDataHeader();

    List getDataBody();
}
