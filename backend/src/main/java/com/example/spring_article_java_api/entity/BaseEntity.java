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
@Getter
public abstract class BaseEntity {
    /***
    * 作成者
    */
    @Column(name="created_by", updatable=false, length=50)
    private Long createdBy;

    /***
    * 更新者
    */
    @Column(name="updated_by", length=50)
    private Long updatedBy;

    /***
     * 作成日
     * 処理はサービス側で行う
     */
    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;

    /***
     * 更新日
     * 処理はサービス側で行う
     */
    @Column(name="updated_at")
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
    public void changeCreatedBy(Long createdBy){
        this.createdBy = createdBy;
    }

    /***
     * 更新者を設定
     * INSERTではNULL許容なのでここでバリデーション
     */
    public void changeUpdatedBy(Long updatedBy){
        this.updatedBy = updatedBy;
    };

    /***
     * 更新日を設定
     * INSERTではNULL許容なのでここでバリデーション
     */
    public void changeUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    /***
     * 継承先で現在時刻を設定するためのメソッド
     */
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }

    /***
     * 削除フラグ更新：削除
     */
    public void deleteFlgDelete(){
        this.deleteFlg = true;
    }

    /***
     * 削除フラグ更新：リストア
     */
    public void deleteFlgRestore(){
        this.deleteFlg = false;
    }
}
