package com.example.spring_article_java_api.dto;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Getter;

/***
 * 記事詳細表示用DTO
 * 記事の基本的な情報を保持する
 */
@Getter
public class GetArticleDetailDto {

    /***
     * タイトル
     */
    private final String title;

    /***
     * 内容
     */
    private final String content;

    /***
     * 表示フラグ
     */
    private final boolean releaseFlg;

    /***
     * 作成日
     */
    private final LocalDateTime createdAt;

    /***
     * 更新日
     */
    private final LocalDateTime updatedAt;

    /***
     * 初期設定用コンストラクタ
     * @param title
     * @param content
     * @param releaseFlg
     * @param createdAt
     * @param updatedAt
     */
    @Builder
    public GetArticleDetailDto(String title, String content, boolean releaseFlg
    , LocalDateTime createdAt, LocalDateTime updatedAt){
      this.title = title;
      this.content = content;
      this.releaseFlg = releaseFlg;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }
}
