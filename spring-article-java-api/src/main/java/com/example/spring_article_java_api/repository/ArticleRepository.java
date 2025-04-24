package com.example.spring_article_java_api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring_article_java_api.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //基本的な保存・更新・削除などはJpaRepositoryから継承されます。
    //新着順取得
    List<Article> findAllByOrderByCreatedAtDesc();

    //特定ユーザーの記事一覧
    @Query("SELECT a FROM Article a WHERE a.userId = :userId ORDER BY a.createdAt DESC")
    List<Article> findByUserId(@Param("userId") int userId, Pageable pageable);

    //閲覧数順記事一覧
    List<Article> findAllByOrderByViewCntDesc(Pageable pageable);

    //like数順記事一覧
    List<Article> findAllByOrderByLikeCntDesc(Pageable pageable);

    //検索結果一覧
    List<Article> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
