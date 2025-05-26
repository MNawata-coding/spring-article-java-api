package com.example.spring_article_java_api.dto.request;

import lombok.Builder;
import lombok.Getter;

/***
 * 記事削除用DTO
 * 記事の削除フラグを保持する
 */
@Getter
public class ArticleDeleteFlgRequestDto {

    /***
     * 削除フラグ
     */
    private final boolean deleteFlg;

    /***
     * 初期設定用コンストラクタ
     * @param deleteFlg
     */
    @Builder
    public ArticleDeleteFlgRequestDto(boolean deleteFlg){
        this.deleteFlg = deleteFlg;
    }
}
