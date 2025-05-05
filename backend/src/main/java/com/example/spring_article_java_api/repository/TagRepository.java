package com.example.spring_article_java_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    //基本的な動作はJpaRepositoryから継承
}
