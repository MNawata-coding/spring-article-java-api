package com.example.spring_article_java_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
