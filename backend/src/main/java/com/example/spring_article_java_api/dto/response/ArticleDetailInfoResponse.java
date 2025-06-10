package com.example.spring_article_java_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ArticleDetailInfoResponse {
    /***
     * タイトル
     */
    private final String title;
    /***
     * 内容
     */
    private final String content;
    /***
     * 公開フラグ
     */
    private final boolean releaseFlg;
}
