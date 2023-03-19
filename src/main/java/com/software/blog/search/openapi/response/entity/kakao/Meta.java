package com.software.blog.search.openapi.response.entity.kakao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Meta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
