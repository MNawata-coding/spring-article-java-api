package com.example.spring_article_java_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

/***
 * 全Entityに共通の監査項目（作成者、更新者、作成日、更新日、削除フラグ）を定義。
 * JPAの@MappedSuperclassによってDBテーブルに継承される。
 */
@MappedSuperclass
public abstract class BaseEntity {
    /***
    * 作成者
    */
    @NotNull
    @Column(name="created_by", updatable=false, length=50)
    @Setter
    private String createdBy;

    /***
    * 更新者
    */
    @Column(name="updated_by", length=50)
    @Setter
    private String updatedBy;

    /***
     * 作成日
     * 処理はサービス側で行う
     */
    @NotNull
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
    @NotNull
    @Column(name="delete_flg", columnDefinition="TINYINT(1)")
    @Setter
    private boolean deleteFlg;
}
