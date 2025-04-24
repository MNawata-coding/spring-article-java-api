package com.example.spring_article_java_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_article_java_api.entity.Article;
import com.example.spring_article_java_api.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository repository;

    public String getArticleTitle(int id){
        Article article;
        article = repository.getReferenceById(id);
        return article.getTitle();
    }
}
