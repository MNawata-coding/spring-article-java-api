package com.example.spring_article_java_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事作成後返却DTO
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticleDetailCreateResponseDto {
    
    /***
     * 記事ID
     */
    private final long articleId;

    /***
     * ユーザーID
     */
    private final long userId;

}
