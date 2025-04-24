package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name="articles")
public class Article extends BaseEntity {

    /***
     * 記事ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    /***
    * ユーザーID
    */
    @Setter
    @Column(name="user_id")
    private int userId;

    /***
     * タイトル
     */
    @NotBlank
    @Size(max=50)
    @Column(name="title", length=50)
    @Setter
    private String title;

    /***
     * 内容(本文)
     */
    @NotBlank
    @Size(max=255)
    @Column(name="content", length=255)
    @Setter
    private String content;

    /***
     * ライク数
     */
    @NotNull
    @Column(name="like_cnt")
    @Setter
    private int likeCnt;

    /***
     * 閲覧数
     */
    @Column(name="view_cnt")
    @Setter
    private int viewCnt;

    /***
     * 表示フラグ
     */
    @NotNull
    @Column(name="release_flg")
    @Setter
    private Boolean releaseFlg;

    /***
     * 投稿予約日
     */
    @Column(name="reservation_day")
    @Setter
    private LocalDateTime reservationDay;

}
