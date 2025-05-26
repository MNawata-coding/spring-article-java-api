package com.example.spring_article_java_api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring_article_java_api.entity.ArticleTag;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.testUtils.TestUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@DataJpaTest
public class ArticleTagRepositoryTest {

    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    ArticleTagRepository repository;

    EntityManager manager;

    @Autowired
    private ArticleTagRepositoryTest(ArticleTagRepository repository, EntityManager manager){
        this.repository = repository;
        this.manager = manager;
    }

    /***
     * flywayで初期データ挿入
     */
    @BeforeAll
    public void flywaySet(){
        TestUtils connect = new TestUtils();
        connect.flywayConnect(mysql, ConstUtils.REP_BASE_LOCATION);
    }

    /***
     * tag_idが取得できること
     */
    @Test
    @DisplayName("タグidが取得できること")
    public void findAllTagId(){
        //取得
        List<ArticleTag> artTag = repository.findAll();
        //emptyチェック
        assertThat(artTag).as("取得チェック")
            .isNotEmpty();
        //タグidを取得
        List<Long> tagIds = artTag.stream().map(s -> s.getArticleTagPK().getTagId()).toList();
        //期待値を設定
        List<Long> expectsIds = List.of(
            1L,
            2L,
            3L,
            4L,
            5L,
            6L,
            7L,
            8L,
            9L
        );
        //期待値と比較する
        assertThat(tagIds).as("外部キー・タグID")
            .containsAll(expectsIds)
            .hasSize(9);
    }

    /***
     * article_idが取得できること
     */
    @Test
    @DisplayName("記事IDが取得できること")
    public void findAllArticleId(){
        //取得
        List<ArticleTag> artTag = repository.findAll();
        //emptyチェック
        assertThat(artTag).as("取得チェック")
            .isNotEmpty();
        //タグidを取得
        List<Long> articleIds = artTag.stream().map(s -> s.getArticleTagPK().getArticleId()).toList();
        //期待値を設定
        List<Long> expectsIds = List.of(
            9L,
            8L,
            7L,
            6L,
            5L,
            4L,
            3L,
            2L,
            1L
        );
        //期待値と比較する
        assertThat(articleIds).as("外部キー・記事ID")
            .containsAll(expectsIds)
            .hasSize(9);
    }

    /***
     * 作成日が取得できること
     */
    @Test
    @DisplayName("作成日が取得できること")
    public void findAllCreatedAt(){
        //取得
        List<ArticleTag> artTag = repository.findAll();
        //emptyチェック
        assertThat(artTag).as("取得チェック")
            .isNotEmpty();
        //タグidを取得
        List<LocalDateTime> createdAt = artTag.stream().map(s -> s.getCreatedAt()).toList();
        //期待値を設定
        List<LocalDateTime> expectsCreatedAt = List.of(
            LocalDateTime.of(2025, 1, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 2, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 3, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 4, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 5, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 6, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 7, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 8, 01, 00, 00, 00 ,00000),
            LocalDateTime.of(2025, 9, 01, 00, 00, 00 ,00000)
        );
        //期待値と比較する
        assertThat(createdAt).as("作成日")
            .containsAll(expectsCreatedAt)
            .hasSize(9);
    }

    /***
     * 登録テスト
     */
    @ParameterizedTest
    @CsvSource({"1, 9", "2, 8", "3, 7", "4, 6"})
    @DisplayName("tagid登録テスト")
    public void insertTagId(Long artId, Long tagId){
        //PK設定
        ArticleTag artTag = new ArticleTag(tagId,artId);
        //保存処理
        repository.save(artTag);
        try{
            manager.flush();
        } catch(PersistenceException e){
            fail();
        }
        //取得
        Optional<ArticleTag> insertData = repository.findById(artTag.getArticleTagPK());
        //Nullチェック
        assertThat(insertData).as("新規登録")
            .isPresent();
        //取得
        Optional<ArticleTag> getArt = repository.findById(artTag.getArticleTagPK());
        //期待値と比較
        assertThat(getArt.get().getArticleTagPK().getArticleId()).as("記事ID")
            .isEqualTo(artId);
        assertThat(getArt.get().getArticleTagPK().getTagId()).as("タグID")
            .isEqualTo(tagId);
        assertThat(getArt.get().getCreatedAt()).as("作成日")
            .isEqualTo(artTag.getCreatedAt());
    }

    /***
     * 登録テスト(失敗)
     */
    @ParameterizedTest
    @CsvSource({"99999, 99999", "12, 18", "13, 17", "14, 16"})
    @DisplayName("tagid登録テスト・失敗")
    public void insertTagIdFail(Long artId, Long tagId){
        //PK設定
        ArticleTag artTag = new ArticleTag(tagId,artId);
        //保存処理
        repository.save(artTag);
        //エラー発生で成功
        assertThatExceptionOfType(PersistenceException.class)
            .isThrownBy(() -> manager.flush());
    }
}
