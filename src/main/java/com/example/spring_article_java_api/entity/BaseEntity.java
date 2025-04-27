package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
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
    @NotNull
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
    @NotNull
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
        if(StringUtils.isBlank(updatedBy)){
            throw new IllegalArgumentException("更新者の値が不正です");
        }
        this.updatedBy = updatedBy;
    };

    /***
     * 更新日を設定
     * INSERTではNULL許容なのでここでバリデーション
     */
    protected void changeUpdatedAt(LocalDateTime updatedAt){
        //作成日よりも前の値だった場合
        if(updatedAt == null) {
            throw new IllegalArgumentException("更新日付の値が不正です");
        } else if(updatedAt.isBefore(this.createdAt)){
            //値がnullの場合(初期はNULL許容なのでアノテーションはなし)
            throw new IllegalArgumentException("更新日が作成日よりも前に設定されています");
        }
        this.updatedAt = updatedAt;
    }

    /***
     * 継承先で現在時刻を設定するためのメソッド
     */
    protected void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    /***
     * 削除フラグ更新
     */
    protected void changeDeleteFlg(boolean deleteFlg){
        this.deleteFlg = deleteFlg;
    }
}
