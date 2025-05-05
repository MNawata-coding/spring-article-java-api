package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/***
 * 全Entityに共通の監査項目（作成者、更新者、作成日、更新日、削除フラグ）を定義。
 * JPAの@MappedSuperclassによってDBテーブルに継承される。
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {
    /***
    * 作成者
    */
    @Column(name="created_by", updatable=false, length=50)
    private String createdBy;

    /***
    * 更新者
    */
    @Column(name="updated_by", length=50)
    private String updatedBy;

    /***
     * 作成日
     * 処理はサービス側で行う
     */
    @Column(name="created_at", updatable=false)
    @Getter
    private LocalDateTime createdAt;

    /***
     * 更新日
     * 処理はサービス側で行う
     */
    @Column(name="updated_at")
    @Getter
    private LocalDateTime updatedAt;

    /***
    * 削除フラグ
    */
    @Column(name="delete_flg", columnDefinition="TINYINT(1)")
    private boolean deleteFlg;

    /***
     * 作成者を更新
     * protectedメソッドを作成し一貫性を保持
     * @param createdBy
     */
    protected void changeCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    /***
     * 更新者を設定
     * INSERTではNULL許容なのでここでバリデーション
     */
    protected void changeUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    };

    /***
     * 更新日を設定
     * INSERTではNULL許容なのでここでバリデーション
     */
    protected void changeUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    /***
     * 継承先で現在時刻を設定するためのメソッド
     */
    protected void setCreatedAt(){
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }

    /***
     * 削除フラグ更新
     */
    protected void changeDeleteFlg(boolean deleteFlg){
        this.deleteFlg = deleteFlg;
    }
}
