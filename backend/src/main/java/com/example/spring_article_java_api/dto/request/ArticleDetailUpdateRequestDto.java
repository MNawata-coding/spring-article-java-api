package com.example.spring_article_java_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/***
 * 記事更新用DTO
 * 記事の更新情報を保持する
 */
@Getter
public class ArticleDetailUpdateRequestDto {

    /***
     * 記事ID
     */
    @Min(0)
    private final Long articleId;

    /***
     * ユーザーID
     */
    @Min(0)
    private final Long userId;

    /***
     * タイトル
     */
    @NotNull
    @Size(max=50)
    private final String title;

    /***
     * 内容
     */
    @NotNull
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
     * @param articleId
     * @param userId
     * @param title
     * @param content
     * @param releaseFlg
     * @param deleteFlg
     */
    @Builder
    public ArticleDetailUpdateRequestDto(Long articleId, Long userId, String title, String content, boolean releaseFlg, boolean deleteFlg){
      this.articleId = articleId;
      this.userId = userId;
      this.title = title;
      this.content = content;
      this.releaseFlg = releaseFlg;
      this.deleteFlg = deleteFlg;
    }
}
