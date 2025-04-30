package com.example.spring_article_java_api.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/***
 * 記事投稿用DTO
 * 記事投稿時に必要なデータを保持する
 */
@Getter
public class InsertArticleDetailDto {

    /***
     * ユーザーID
     */
    @Min(0)
    private final int userId;

    /***
     * タイトル
     */
    @NotBlank
    @Size(max=50)
    private final String title;

    /***
     * 内容(本文)
     */
    @NotBlank
    @Size(max=255)
    private final String content;

    /***
     * 公開フラグ
     */
    private final boolean releaseFlg;

    /***
     * 削除フラグ
     */
    private final boolean deleteFlg;

    
    /***
     * 初期設定用コンストラクタ
     * @param userId
     * @param title
     * @param content
     * @param releaseFlg
     * @param deleteFlg
     */
    @Builder
    public InsertArticleDetailDto(int userId, String title, String content, boolean releaseFlg, boolean deleteFlg){
      this.userId = userId;
      this.title = title;
      this.content = content;
      this.releaseFlg = releaseFlg;
      this.deleteFlg = deleteFlg;
    }
}
