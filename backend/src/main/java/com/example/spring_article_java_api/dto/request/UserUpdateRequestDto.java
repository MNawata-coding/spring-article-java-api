package com.example.spring_article_java_api.dto.request;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***
 * ユーザー情報更新DTO
 * ユーザーの更新情報を保持
 */
@RequiredArgsConstructor
@Getter
@Builder
public class UserUpdateRequestDto {

    /***
     * ユーザーID
     */
    @Min(0)
    private final long id;

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
