package com.example.spring_article_java_api.dto.response;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import lombok.Builder;
import lombok.Getter;

/***
 * ユーザー情報取得DTO
 * 基本的なユーザー情報を保持する
 */
@Getter
public class UserResponseDto {

    /***
     * ユーザーID
     */
    private final int userId;

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

    /***
     * 初期設定用コンストラクタ
     * @param userId
     * @param name
     * @param email
     * @param role
     */
    @Builder
    public UserResponseDto(int userId, String name, String email, ROLE role){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
