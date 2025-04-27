package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
@Table(name="articles")
@NoArgsConstructor
public class Article extends BaseEntity {

    /***
     * 記事ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    /***
    * ユーザーID
    */
    @Column(name="user_id")
    @Getter
    private int userId;

    /***
     * タイトル
     */
    @NotBlank
    @Size(max=50)
    @Column(name="title", length=50)
    @Getter
    @Setter
    private String title;

    /***
     * 内容(本文)
     */
    @NotBlank
    @Size(max=255)
    @Column(name="content", length=255)
    @Getter
    @Setter
    private String content;

    /***
     * ライク数
     */
    @NotNull
    @Column(name="like_cnt")
    @Getter
    private int likeCnt;

    /***
     * 閲覧数
     */
    @Column(name="view_cnt")
    @Getter
    private int viewCnt;

    /***
     * 表示フラグ
     */
    @NotNull
    @Column(name="release_flg")
    @Getter
    @Setter
    private Boolean releaseFlg;

    /***
     * 投稿予約日
     */
    @Column(name="reservation_day")
    @Getter
    private LocalDateTime reservationDay;

    /***
     * コンストラクタ
     * 必須項目を設定する
     */
    public Article(int userId, String title, String content, Boolean releaseFlg, String createdBy){
        //必須項目をコンストラクタで設定し、漏れがないようにする。
        //各値の整合性チェック
        validateUserId(userId);

        //Entityに値を設定する
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.releaseFlg = releaseFlg;
        changeCreatedBy(createdBy);
        setCreatedAt();
    }

    /***
     * ユーザーID設定
     */
    public void changeUserId(int userId) {
        //ユーザーIDが0よりも小さい場合
        validateUserId(userId);
        this.userId = userId;
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

    /**
     * 投稿予約日設定
     * @return
     * @exception IllegalArgumentException
     */
    public void changeReservationDay(LocalDateTime reservationDay) {
        //現在時刻よりも前の場合
        validateReservationDay(reservationDay);
        this.reservationDay = reservationDay;
    }

    /***
     * ユーザーIDのチェック
     * @param userId
     * @exception IllegalArgumentException
     */
    private void validateUserId(int userId){
        //ユーザーIDが0よりも小さい場合
        if(userId < 0){
            throw new IllegalArgumentException("ユーザーIDが0よりも小さいです");
        }
    }

    /***
     * 予約日チェック
     * @param reservationDay
     * @exception IllegalArgumentException
     */
    private void validateReservationDay(LocalDateTime reservationDay){
        //現在時刻よりも前の場合
        if(reservationDay.isBefore(LocalDateTime.now(ZoneId.of("Asia/Tokyo")))){
            throw new IllegalArgumentException("予約日が現在時刻よりも前に設定されています");
        }
    }
}
