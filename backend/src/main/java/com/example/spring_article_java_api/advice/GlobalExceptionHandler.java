package com.example.spring_article_java_api.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spring_article_java_api.dto.ResultDto;
import com.example.spring_article_java_api.exception.ArticleCreateException;
import com.example.spring_article_java_api.utils.MessageUtils;
import com.example.spring_article_java_api.utils.ResultUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    
    private ResultUtils result;
    private MessageUtils message;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * 記事作詞時にエラーが発生
     * @return
     */
    @ExceptionHandler(ArticleCreateException.class)
    public ResultDto handleArticleCreate(){
        logger.info("DBに記事を保存できませんでした");
        return result.error(message.errorMessage());
    }
}
