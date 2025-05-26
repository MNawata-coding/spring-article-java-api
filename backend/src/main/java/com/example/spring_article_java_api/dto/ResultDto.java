package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * 結果返却DTO
 * Service内での処理結果共有で利用する
 * Controllerからの返却はしない
 */
@Getter
@NoArgsConstructor
public class ResultDto {
    
    /***
     * 処理結果
     */
    @Setter
    private boolean result;

    /***
     * メッセージ
     * 成功の場合もメッセージを設定
     */
    @Setter
    private String message;

    /***
     * エラー発生時のメッセージ
     */
    private String errorMessage;

    /***
     * エラーコード
     */
    @Setter
    private Long errorCode;

    /***
     * 初期設定用コンストラクタ
     * @param result
     * @param message
     */
    @Builder
    public ResultDto(boolean result, String message, String errorMessage){
        this.result = result;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
