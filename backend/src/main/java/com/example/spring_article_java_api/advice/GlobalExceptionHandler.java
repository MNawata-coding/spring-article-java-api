package com.example.spring_article_java_api.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spring_article_java_api.dto.ResultDto;
import com.example.spring_article_java_api.exception.ArticleCreateException;
import com.example.spring_article_java_api.utils.MessageUtils;
import com.example.spring_article_java_api.utils.ResultUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    private final ResultUtils result;
    private final MessageUtils message;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * 記事作詞時にエラーが発生
     * @return
     */
    @ExceptionHandler(ArticleCreateException.class)
    public ResponseEntity<ResultDto> handleArticleCreate(){
        //ログに出力
        logger.info("DBに記事を保存できませんでした");
        //サーバーエラーで返却する
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.error(message.errorMessage()));
    }
}
