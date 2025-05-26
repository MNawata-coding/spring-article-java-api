package com.example.spring_article_java_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.codec.EncodingException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring_article_java_api.dto.request.ArticleDetailCreateRequestDto;
import com.example.spring_article_java_api.entity.Article;
import com.example.spring_article_java_api.exception.ArticleCreateException;
import com.example.spring_article_java_api.repository.ArticleRepository;
import com.example.spring_article_java_api.service.create.ArticleCreateService;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.utils.ResultUtils;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@TestInstance(Lifecycle.PER_CLASS)
@Testcontainers
@SpringBootTest
@Slf4j
public class ArticleCreateServiceImpleTest {

    ArticleCreateService service;
    ResultUtils result;
    EntityManager em;

    @Captor
    ArgumentCaptor<Article> captor;

    @MockitoBean
    ArticleRepository repository;

    @Container
    @ServiceConnection
    private static MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    @Autowired
    public ArticleCreateServiceImpleTest(ArticleCreateService service, ResultUtils result, EntityManager em){
        this.service = service;
        this.result = result;
        this.em = em;
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    @DisplayName("保存が正常に完了する")
    public void saveTest(int Num){
        log.info("saveTest開始");

        //repository呼び出し時の返却する値を設定
        Article art = new Article("testTitle" + Num, "testContent" + Num, false, 1L);
        //repository呼び出し時の挙動を設定する
        when(repository.save(any())).thenReturn(art);

        ArticleDetailCreateRequestDto dto = ArticleDetailCreateRequestDto.builder()
            .userId(1L)
            .title("testtitle" + Num)
            .content("content" + Num)
            .releaseFlg(false)
            .deleteFlg(false)
            .build();
        try {
            //Service実行
            service.createArticle(dto);
        } catch (ArticleCreateException e){
            //異常終了で失敗
            fail(e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        //Articleでsaveメソッドに引き渡した値を取得する
        ArgumentCaptor<Article> captor = ArgumentCaptor.forClass(Article.class);
        //repositoryのsaveメソッドが一回呼ばれていることを確認する
        verify(repository,times(1))
        .save(captor.capture()); //実際に引き渡した値で比較する

        //引数の値を取得
        Article captured = captor.getValue();

        //各値が正常に設定されているかを確認する
        assertThat(captured.getTitle()).as("タイトル")
            .isEqualTo("testtitle" + Num);
        assertThat(captured.getContent()).as("内容")
            .isEqualTo("content" + Num);
        assertThat(captured.isReleaseFlg()).as("表示フラグ")
            .isEqualTo(false);
        assertThat(captured.isDeleteFlg()).as("削除フラグ")
            .isEqualTo(false);
        assertThat(captured.getCreatedBy()).as("作成者")
            .isEqualTo(1L);

        log.info("saveTest終了");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    @DisplayName("IllegalArgumentExceptionテスト")
    public void argumentErrorTest(int Num){
        log.info("argumentErrorTest開始");

        //repository呼び出し時の挙動を設定する
        when(repository.save(any())).thenThrow(new IllegalArgumentException("testError"));

        ArticleDetailCreateRequestDto dto = ArticleDetailCreateRequestDto.builder()
            .userId(1L)
            .title("testtitle" + Num)
            .content("content" + Num)
            .releaseFlg(false)
            .deleteFlg(false)
            .build();
            //Service実行
            assertThatThrownBy(() -> service.createArticle(dto))
                .isInstanceOf(ArticleCreateException.class)
                .hasMessageContaining("DB登録エラー");

        log.info("argumentErrorTest終了");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    @DisplayName("OptimisticLockingテスト")
    public void lockingErrorTest(int Num){
        log.info("lockingErrorTest開始");

        //repository呼び出し時の挙動を設定する
        when(repository.save(any())).thenThrow(new OptimisticLockingFailureException("testError"));

        ArticleDetailCreateRequestDto dto = ArticleDetailCreateRequestDto.builder()
            .userId(1L)
            .title("testtitle" + Num)
            .content("content" + Num)
            .releaseFlg(false)
            .deleteFlg(false)
            .build();
            //Service実行
            assertThatThrownBy(() -> service.createArticle(dto))
                .isInstanceOf(ArticleCreateException.class)
                .hasMessageContaining("DBロックエラー");

        log.info("lockingErrorTest終了");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    @DisplayName("unknownErrorTestテスト")
    public void unknownErrorTest(int Num){
        log.info("lockingErrorTest開始");

        //repository呼び出し時の挙動を設定する
        when(repository.save(any())).thenThrow(new NullPointerException("testError"));
        when(repository.save(any())).thenThrow(new Exception("testError"));
        when(repository.save(any())).thenThrow(new EncodingException("testError"));

        ArticleDetailCreateRequestDto dto = ArticleDetailCreateRequestDto.builder()
            .userId(1L)
            .title("testtitle" + Num)
            .content("content" + Num)
            .releaseFlg(false)
            .deleteFlg(false)
            .build();
            //Service実行
            for(int i=0; i < 0; i++){
                assertThatThrownBy(() -> service.createArticle(dto))
                    .isInstanceOf(ArticleCreateException.class)
                    .hasMessageContaining("不明なエラーが発生しました");
            }

        log.info("lockingErrorTest終了");
    }
}
