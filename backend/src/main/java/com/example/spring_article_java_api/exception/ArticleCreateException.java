package com.example.spring_article_java_api.exception;

public class ArticleCreateException extends RuntimeException{

    /***
     * コンストラクタ
     * @param message
     */
    public ArticleCreateException(String message){
        super(message);
    }
}
