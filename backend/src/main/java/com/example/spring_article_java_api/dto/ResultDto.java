package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;

/***
 * 結果返却DTO
 * Service内での処理結果共有で利用する
 * Controllerからの返却はしない
 */
@Getter
public class ResultDto {
    
    /***
     * 処理結果
     */
    private final boolean result;

    /***
     * メッセージ
     * 成功の場合もメッセージを設定
     */
    private final String message;

    /***
     * 初期設定用コンストラクタ
     * @param result
     * @param message
     */
    @Builder
    public ResultDto(boolean result, String message){
        this.result = result;
        this.message = message;
    }
}
