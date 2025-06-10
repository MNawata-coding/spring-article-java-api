package com.example.spring_article_java_api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.spring_article_java_api.dto.request.ArticleDetailCreateRequestDto;
import com.example.spring_article_java_api.dto.response.ArticleDetailCreateResponseDto;
import com.example.spring_article_java_api.service.create.ArticleCreateService;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.utils.MessageUtils;
import com.example.spring_article_java_api.utils.ResultUtils;

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
@WebMvcTest
public class ArticleCreateControllerTest {

    ArticleCreateController controller;

    @Autowired
    public ArticleCreateControllerTest(ArticleCreateController controller){
        this.controller = controller;
    }

    @MockitoBean
    ArticleCreateService service;

    @MockitoBean
    MessageUtils message;

    @MockitoBean
    ResultUtils result;
    
    @Captor
    ArgumentCaptor<ArticleCreateService> createService;
    
    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    @Test
    @DisplayName("createTest_Normal")
    public void createTest(){
        //コントローラーへ引き渡す引数設定
        ArticleDetailCreateRequestDto dto = ArticleDetailCreateRequestDto.builder()
                                    .userId(1L)
                                    .title("createControllerTestTitle")
                                    .content("createControlerContent")
                                    .releaseFlg(false)
                                    .deleteFlg(false)
                                    .build();

        //サービスクラスから返却される値を設定
        ArticleDetailCreateResponseDto  expects = ArticleDetailCreateResponseDto.builder()
                                        .articleId(999L)
                                        .userId(999L)
                                        .build();

        //サービスをモック化する
        when(service.createArticle(any())).thenReturn(expects);

        //コントローラーから返却される期待値
        ResponseEntity<ArticleDetailCreateResponseDto> expectsEntity = ResponseEntity.ok(expects);

        //controller実行
        ResponseEntity<ArticleDetailCreateResponseDto> result = controller.articleSave(dto);

        //返却された値を比較する
        assertThat(result).as("返却値")
            .isEqualTo(expectsEntity);
    }
}
