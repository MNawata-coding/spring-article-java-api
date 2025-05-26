package com.example.spring_article_java_api.dto.response;


import lombok.Builder;
import lombok.Getter;

/***
 * 記事一覧用DTO
 * 記事のIDとタイトルを保持する
 */
@Getter
public class ArticleInfoResponseDto {

    /***
     * 記事ID
     * 記事URLに使用する
     */
    private final int articleId;

    /***
     * 記事のタイトル
     */
    private final String title;

    /***
     * 初期設定用コンストラクタ
     * @param articleId
     * @param title
     */
    @Builder
    public ArticleInfoResponseDto(int articleId, String title){
        this.articleId = articleId;
        this.title = title;
    }
}
