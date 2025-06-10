package com.example.spring_article_java_api.dto.request;


import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * ユーザー登録DTO
 * ユーザ登録時に必要なデータを保持する
 */
@RequiredArgsConstructor
@Getter
@Builder
public class UserCreateRequestDto {

    /***
     * ユーザー名
     */
    @NotBlank
    @Size(max=50)
    private final String name;

    /***
     * メールアドレス
     */
    @NotBlank
    @Size(max=50)
    private final String email;

    /***
     * 役職
     * ADMIN または USER
     */
    @NotNull
    private final ROLE role;

}
