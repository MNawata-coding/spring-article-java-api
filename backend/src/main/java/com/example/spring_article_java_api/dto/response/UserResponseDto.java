package com.example.spring_article_java_api.dto.response;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * ユーザー情報取得DTO
 * 基本的なユーザー情報を保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class UserResponseDto {

    /***
     * ユーザーID
     */
    private final long userId;

    /***
     * ユーザー名
     */
    private final String name;

    /***
     * メールアドレス
     */
    private final String email;

    /***
     * 役職
     */
    private final ROLE role;

}
