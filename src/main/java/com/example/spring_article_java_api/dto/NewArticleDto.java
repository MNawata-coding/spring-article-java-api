package com.example.spring_article_java_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewArticleDto {

    /***
     * 記事ID
     * 記事URLに使用する
     */
    @NotNull
    int articleId;

    /***
     * 記事のタイトル
     */
    @NotBlank
    @Size(min=1, max=50)
    String title;
}
