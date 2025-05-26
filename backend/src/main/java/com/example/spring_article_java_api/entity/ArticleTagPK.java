package com.example.spring_article_java_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ArticleTagPK {
    /***
    * タグテーブル.タグID
    */
    @Column(name="tag_id")
    private Long tagId;

    /***
    * 記事テーブル.記事ID
    */
    @Column(name="article_id")
    private Long articleId;

    /***
     * 初期設定用コンストラクタ
     */
    protected ArticleTagPK(Long tagId, Long articleId){
        this.tagId = tagId;
        this.articleId = articleId;
    }
}
