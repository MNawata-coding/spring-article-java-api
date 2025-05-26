package com.example.spring_article_java_api.dto.request;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/***
 * ユーザー情報更新DTO
 * ユーザーの更新情報を保持
 */
@Getter
public class UserUpdateRequestDto {

    /***
     * ユーザーID
     */
    @Min(0)
    private final Long id;

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
     * @param id
     * @param name
     * @param email
     * @param role
     */
    @Builder
    public UserUpdateRequestDto(Long id, String name, String email, ROLE role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
