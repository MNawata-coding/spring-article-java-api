package com.example.spring_article_java_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ArticleUpdateResponse {
    /***
     * 記事ID
     */
    private final long articleID;

    /***
     * ユーザーID
     */
    private final long userId;
}
