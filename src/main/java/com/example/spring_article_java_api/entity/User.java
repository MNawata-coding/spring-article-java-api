package com.example.spring_article_java_api.entity;

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
@Getter
@NoArgsConstructor
@Table(name="users")
public class User extends BaseEntity {

    /***
     * ユーザーID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /***
     * ユーザー名
     */
    @NotBlank
    @Size(min = 1, max=50)
    @Setter
    private String name;

    /***
     * メールアドレス
     */
    @NotBlank
    @Size(max=50)
    @Setter
    private String email;

    /***
     * 役職
     */
    @NotBlank
    @Setter
    @Column(name="role", columnDefinition="ENUM('USER', 'ADMIN')")
    private String role;

}
