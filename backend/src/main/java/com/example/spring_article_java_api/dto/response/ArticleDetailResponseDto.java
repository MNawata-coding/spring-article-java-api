package com.example.spring_article_java_api.dto.response;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事詳細表示用DTO
 * 記事の基本的な情報を保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticleDetailResponseDto {

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

}
