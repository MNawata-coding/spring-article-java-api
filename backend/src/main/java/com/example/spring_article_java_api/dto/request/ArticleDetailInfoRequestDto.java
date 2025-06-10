package com.example.spring_article_java_api.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ArticleDetailInfoRequestDto {
    /***
     * ユーザーID
     */
    @Min(0)
    private final long userId;

    /***
     * 記事ID
     */
    @Min(0)
    private final long articleId;
}
