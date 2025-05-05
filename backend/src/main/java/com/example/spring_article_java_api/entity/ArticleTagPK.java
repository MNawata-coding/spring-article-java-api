package com.example.spring_article_java_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ArticleTagPK {
    /***
    * タグテーブル.タグID
    */
    @NotNull
    @Column(name="tag_id")
    private int tagId;

    /***
    * 記事テーブル.記事ID
    */
    @NotNull
    @Column(name="article_id")
    private int articleId;

    /***
     * 初期設定用コンストラクタ
     */
    protected ArticleTagPK(int tagId, int articleId){
        this.tagId = tagId;
        this.articleId = articleId;
    }
}
