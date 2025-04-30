package com.example.spring_article_java_api.entity;

import com.example.spring_article_java_api.constant.EnumList.ROLE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="users")
public class User extends BaseEntity {

    /***
     * ユーザーID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    /***
     * ユーザー名
     */
    @NotBlank
    @Size(max=50)
    @Setter
    @Getter
    private String name;

    /***
     * メールアドレス
     */
    @NotBlank
    @Size(max=50)
    @Setter
    @Getter
    private String email;

    /***
     * 役職
     */
    @NotBlank
    @Column(name="role", columnDefinition="ENUM('USER', 'ADMIN')")
    @Setter
    @Getter
    private ROLE role;

    /***
     * 初期設定用コンストラクタ
     * @param name
     * @param email
     * @param createdBy
     * @param role
     * @param deleteFlg
     */
    public User(String name,String email, String createdBy, ROLE role, boolean deleteFlg){
        //初期設定する値を記載
        this.name = name;
        this.email = email;
        this.role = role;
        setCreatedAt();
        changeCreatedBy(createdBy);
        changeDeleteFlg(deleteFlg);
    }

}
