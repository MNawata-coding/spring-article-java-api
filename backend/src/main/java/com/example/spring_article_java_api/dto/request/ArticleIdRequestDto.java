package com.example.spring_article_java_api.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

/***
 * 記事ID引き渡し用DTO
 * 記事の一意なIDを保持する
 */
@Getter
public class ArticleIdRequestDto {
    /***
     * 記事ID
     * 記事URLに使用する
     */
    @Min(1)
    private final Long articleId;

    /***
     * 初期設定用コンストラクタ
     * @param articleId
     */
    @Builder
    public ArticleIdRequestDto(Long articleId){
        this.articleId = articleId;
    }
}
