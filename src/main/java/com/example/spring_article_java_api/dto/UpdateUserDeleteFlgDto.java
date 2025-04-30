package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;

/***
 * ユーザー削除用DTO
 * 削除フラグを保持する
 */
@Getter
public class UpdateUserDeleteFlgDto {

    /***
     * 削除フラグ
     */
    private final boolean deleteFlg;

    /***
     * 初期設定用コンストラクタ
     * @param deleteFlg
     */
    @Builder
    public UpdateUserDeleteFlgDto(boolean deleteFlg){
        this.deleteFlg = deleteFlg;
    }
}
