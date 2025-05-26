package com.example.spring_article_java_api.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MessageUtils {

    private final MessageSource messageSource;

    /***
     * 異常終了の文字列を返却する
     * @return String message
     */
    public String errorMessage(){
        return constMessage("message.NORMAL");
    }

    /***
     * 正常終了の文字列を返却する
     * @return String message
     */
    public String normalMessage(){
        return constMessage("message.NORMAL");
    }

    /***
     * 任意の文字を受け取り
     */
    public String anyString(String text){
        return messageSource.getMessage(text, null, LocaleContextHolder.getLocale());
    }

    /***
     * 引数にあった文字列を返却する
     */
    public String constMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
