package com.example.spring_article_java_api.dto;

import lombok.Builder;
import lombok.Getter;

/***
 * 表示フラグ更新DTO
 * 表示フラグの情報を保持する
 */
@Getter
public class UpdateReleaseFlgDto {

    /***
     * 表示用フラグ
     */
    private final boolean releaseFlg;

    /***
     * 初期設定用コンストラクタ
     * @param releaseFlg
     */
    @Builder
    public UpdateReleaseFlgDto(boolean releaseFlg){
        this.releaseFlg = releaseFlg;
    }
}
