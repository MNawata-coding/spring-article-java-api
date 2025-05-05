package com.example.spring_article_java_api.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.radcortez.flyway.test.annotation.DataSource;
import com.radcortez.flyway.test.annotation.FlywayTest;

@SpringBootTest
@FlywayTest(@DataSource(SpringDataSourceProvider.class))
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository repository;

    //マイグレーションファイルの配置場所
    final private String migrationFile = "db/ArticleRepository";

    //作成日テスト用url
    final private String createdAtDesc = migrationFile + "/orderByCreatedAt";

    /***
     * 作成日順テスト
     * @throws Exception
     */
    @FlywayTest(additionalLocations = createdAtDesc)
    @Test
    public void findAllByOrderByCreatedAtDescTest() throws Exception {
        String title = "";
        System.out.println(title);
        Assert.isTrue(true, title);
    }

    // /***
    //  * ユーザーID特定テスト
    //  * @throws Exception
    //  */
    // public void findByUserIdTest() throws Exception {
    //     repository.save(null);
    // }

    // /***
    //  * 閲覧数順テスト
    //  * @throws Exception
    //  */
    // public void findAllByOrderByViewCntDescTest() throws Exception {
    //     repository.save(null);
    // }

    // /***
    //  * お気に入り数順テスト
    //  * @throws Exception
    //  */
    // public void findAllByOrderByLikeCntDescTest() throws Exception {
    //     repository.save(null);
    // }

    // /***
    //  * タイトル検索テスト
    //  * @throws Exception
    //  */
    // public void findByTitleContainingIgnoreCaseTest() throws Exception {
    //     repository.save(null);
    // }

}
