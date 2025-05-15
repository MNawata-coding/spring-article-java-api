package com.example.spring_article_java_api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //検索結果一覧
    List<Article> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
