package com.example.spring_article_java_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class ArticleTagPK {
    /***
    * タグテーブル.タグID
    */
    @NotNull
    @Column(name="tag_id")
    @Setter
    private int tagId;

    /***
    * 記事テーブル.記事ID
    */
    @NotNull
    @Column(name="article_id")
    @Setter
    private int articleId;
}
