package com.example.spring_article_java_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.ArticleTag;
import com.example.spring_article_java_api.entity.ArticleTagPK;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, ArticleTagPK>{
    //基本的な操作はJpaRepositoryから継承
}
