package com.example.spring_article_java_api.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事投稿用DTO
 * 記事投稿時に必要なデータを保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticleDetailCreateRequestDto {

    /***
     * ユーザーID
     */
    @Min(0)
    private final long userId;

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

}
