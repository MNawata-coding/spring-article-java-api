package com.example.spring_article_java_api.dto.response;


import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/***
 * ユーザー登録DTO
 * ユーザ登録時に必要なデータを保持する
 */
@Getter
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

    /***
     * 初期設定用コンストラクタ
     * @param name
     * @param email
     * @param role
     */
    @Builder
    public UserCreateRequestDto(String name, String email, ROLE role){
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
