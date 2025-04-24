package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="articles_tags")
public class ArticleTag {

    /***
     * 複合キー
     * タグID + 記事ID
     */
    @EmbeddedId
    private ArticleTagPK articleTagPK;
    
    /***
     * 作成日
     */
    @NotNull
    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;
}
