package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name="articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    /***
     * 記事ID
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
    * ユーザーID
    */
    @Column(name="user_id", nullable = false)
    private int userId;

    /***
     * タイトル
     */
    @Column(name="title", length=50, nullable = false)
    @Setter
    private String title;

    /***
     * 内容(本文)
     */
    @Column(name="content", length=255)
    @Setter
    private String content;

    /***
     * ライク数
     */
    @Column(name="like_cnt")
    private Long likeCnt;

    /***
     * 閲覧数
     */
    @Column(name="view_cnt")
    private Long viewCnt;

    /***
     * 表示フラグ
     */
    @Column(name="release_flg")
    @Setter
    private boolean releaseFlg;

    /***
     * 投稿予約日
     */
    @Column(name="reservation_day")
    @Setter
    private LocalDateTime reservationDay;

    /***
     * コンストラクタ
     * 必須項目を設定する
     * @param title
     * @param content
     * @param releaseFlg
     * @param createdBy
     */
    public Article(String title, String content, boolean releaseFlg, Long createdBy){
        //必須項目をコンストラクタで設定し、漏れがないようにする。
        //Entityに値を設定する
        this.title = title;
        this.content = content;
        this.releaseFlg = releaseFlg;
        changeCreatedBy(createdBy);
        setCreatedAt();
        deleteFlgRestore();
        this.viewCnt = 0L;
        this.likeCnt = 0L;
    }

    /**
     * お気に入り数設定
     */
    public void addLikeCnt() {
        //ライク数をプラス1する
        this.likeCnt++;
    }

    /**
     * 閲覧数設定
     */
    public void addViewCnt() {
        //閲覧数をプラス1する
        this.viewCnt++;
    }
}
