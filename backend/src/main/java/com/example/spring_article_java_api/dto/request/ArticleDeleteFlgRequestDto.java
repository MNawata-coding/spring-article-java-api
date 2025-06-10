package com.example.spring_article_java_api.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * 記事削除用DTO
 * 記事の削除フラグを保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class ArticleDeleteFlgRequestDto {

    /***
     * 削除フラグ
     */
    private final boolean deleteFlg;
}
