package com.example.spring_article_java_api.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事ID引き渡し用DTO
 * 記事の一意なIDを保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticlePrivateRequestDto {
    /***
     * 記事ID
     * 記事URLに使用する
     */
    @Min(1)
    private final long articleId;

}
