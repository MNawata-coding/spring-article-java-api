package com.example.spring_article_java_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ArticlePrivateResponseDto {
    /***
     * 記事ID
     */
    private final long aritcleId;
}
