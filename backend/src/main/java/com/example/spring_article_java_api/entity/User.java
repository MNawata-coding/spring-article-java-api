package com.example.spring_article_java_api.entity;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name="users")
public class User extends BaseEntity {

    /***
     * ユーザーID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /***
     * ユーザー名
     */
    @Setter
    private String name;

    /***
     * メールアドレス
     */
    @Setter
    private String email;

    /***
     * 役職
     */
    @Column(name="role", columnDefinition="ENUM('USER', 'ADMIN')")
    @Setter
    @Enumerated(EnumType.STRING)
    private ROLE role;

    /***
     * 初期設定用コンストラクタ
     * @param name
     * @param email
     * @param createdBy
     * @param role
     * @param deleteFlg
     */
    public User(String name,String email, Long createdBy, ROLE role, boolean deleteFlg){
        //初期設定する値を記載
        this.name = name;
        this.email = email;
        this.role = role;
        setCreatedAt();
        changeCreatedBy(createdBy);
        deleteFlgRestore();
    }
}
