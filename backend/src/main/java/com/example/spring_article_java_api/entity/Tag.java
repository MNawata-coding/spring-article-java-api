package com.example.spring_article_java_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Table(name="tags")
public class Tag  extends BaseEntity {

    /***
     * タグID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long tagId;

    /***
    * タグ名称
    */
    @Column(name="tagname", length=50)
    @Setter
    private String tagName;

    /***
     * 初期化用コンストラクタ
     * @param tagName
     * @param createdBY
     * @param deleteFlg
     */
    public Tag(String tagName, Long createdBY, boolean deleteFlg){
        //必須項目をコンストラクタで設定
        this.tagName = tagName;
        setCreatedAt();
        changeCreatedBy(createdBY);
        deleteFlgRestore();
    }

}
