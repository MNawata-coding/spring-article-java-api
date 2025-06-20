package com.example.spring_article_java_api.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事一覧用DTO
 * 記事のIDとタイトルを保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticleInfoResponseDto {

    /***
     * 記事ID
     * 記事URLに使用する
     */
    private final long articleId;

    /***
     * 記事のタイトル
     */
    private final String title;

}
