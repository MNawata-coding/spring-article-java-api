package com.example.spring_article_java_api.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring_article_java_api.entity.Article;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.testUtils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@DataJpaTest
public class ArticleRepositoryTest {
    
    ArticleRepository repository;
    
    //テスト実行前にmysql9のイメージを取得、コンテナを起動
    //()内でバージョンを指定
    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    @Autowired
    public ArticleRepositoryTest(ArticleRepository repository){
        this.repository = repository;
    }
    
    /***
     * 共通データ挿入
     * マスタデータなどがある場合はFlywayで初期データを挿入
     */
    @BeforeAll
    public void flywaySet(){
        TestUtils connect = new TestUtils();
        connect.flywayConnect(mysql, ConstUtils.REP_BASE_LOCATION);
    }

    /***
     * タイトル検索テスト
     * @throws Exception
     */
    @Test
    public void findByTitleContainingIgnoreCaseTest() throws Exception {
        //title1に合致する値を取得(大文字)
        List<Article> findUpperCase = repository.findByTitleContainingIgnoreCase("Title1", null);
        //ID=1の値が取得できているか確認
        assertThat(findUpperCase.get(0)).as("title1検索").isEqualTo(repository.findById(1).get());
        //title1に合致する値を取得(小文字)
        List<Article> findLowerCase = repository.findByTitleContainingIgnoreCase("title1", null);
        //ID=1の値が取得できているか確認
        assertThat(findLowerCase.get(0)).as("title1検索").isEqualTo(repository.findById(1).get());

        //値を登録
        Article insertArt001 = new Article(
            "titleTest001",
            99999L,
            "contentTest001",
            false,
            100001L
        );
        repository.save(insertArt001);

        //値を登録
        Article insertArt002 = new Article(
            "titletest002",
            99990L,
            "contentTest002",
            false,
            100002L
        );
        repository.save(insertArt002);

        //登録した値のみ検索する
        List<Article> searchLowerArticles = repository.findByTitleContainingIgnoreCase("TITLETEST", null);
        List<Article> searchUpperArticles = repository.findByTitleContainingIgnoreCase("titletest", null);
        //期待値を登録する
        List<Article> expectsList = List.of(
            insertArt001,
            insertArt002
        );
        //大文字、小文字、期待値を比較
        assertThat(searchLowerArticles).as("登録した値を検索")
            .isEqualTo(searchUpperArticles)
            .isEqualTo(expectsList);
    }

}
