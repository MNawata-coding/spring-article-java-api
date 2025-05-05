package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;

/***
 * タグ削除用dto
 * 削除フラグを保持する
 */
@Getter
public class UpdateTagDeleteFlgDto {

    /***
     * 削除フラグ
     */
    private final boolean deleteFlg;

    /***
     * 初期設定用コンストラクタ
     * @param deleteFlg
     */
    @Builder
    public UpdateTagDeleteFlgDto(boolean deleteFlg){
        this.deleteFlg = deleteFlg;
    }
}
