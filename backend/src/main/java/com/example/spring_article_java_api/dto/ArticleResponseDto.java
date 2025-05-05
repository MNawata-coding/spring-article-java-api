package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;

/***
 * 記事ID引き渡し用DTO
 * 記事の一意なIDを保持する
 */
@Getter
public class ArticleResponseDto {
    /***
     * 記事ID
     * 記事URLに使用する
     */
    private final int articleId;

    /***
     * 初期設定用コンストラクタ
     * @param articleId
     */
    @Builder
    public ArticleResponseDto(int articleId){
        this.articleId = articleId;
    }
}
