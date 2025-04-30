package com.example.spring_article_java_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_article_java_api.service.ArticleService;

@RestController
@RequestMapping("api")
public class ArticleController {

    @Autowired
    ArticleService articleservice;

    @GetMapping("GetArticle")
    public String GetArticle(){
        String title;
        //サービス層を呼び出し、記事タイトルを取得
        title = articleservice.getArticleTitle(0);
        return title;
    }
}
