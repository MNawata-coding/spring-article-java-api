package com.example.spring_article_java_api.utils;

import org.springframework.stereotype.Component;

import com.example.spring_article_java_api.dto.ResultDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ResultUtils {

    private final MessageUtils message;

    /***
     *  正常終了
     * @return
     */
    public ResultDto success(){
        return ResultDto.builder()
                .result(true)
                .message(message.normalMessage())
                .errorMessage(null)
                .build();
    }

    /***
     *  異常終了
     * @return
     */
    public ResultDto error(String errorMessage){
        return ResultDto.builder()
                .result(false)
                .message(message.errorMessage())
                .errorMessage(errorMessage)
                .build();
    }

}
