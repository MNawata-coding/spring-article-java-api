package com.example.spring_article_java_api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.AbstractAssert;

public class ArticleAssertUtil extends AbstractAssert<ArticleAssertUtil, List<?>>{

    /***
     * コンストラクタでassertjクラスにリストとクラスを引き渡す
     */
    public ArticleAssertUtil(List<?> actual){
        super(actual, ArticleAssertUtil.class);
    }

    /***
     * assertThatから使用できるようにする
     */
    public static ArticleAssertUtil assertThat(List<?> actual){
        return new ArticleAssertUtil(actual);
    }

    /***
     * List内の値が全て0であることを確認
     */
    public ArticleAssertUtil isAllZero(List<?> actual){
        //null排除
        isNotNull();
        //ゼロチェック
        if(actual.stream().allMatch(s -> s == BigDecimal.ZERO)){
            //失敗時のメッセージを設定
            failWithMessage("リストの値が0ではありません", actual);
        }
        return this;
    }

    /***
     * List内が全てNullであることを確認
     */
    public ArticleAssertUtil isAllNull(List<?> actual){
        isNotNull();
        if(actual.stream().allMatch(Boolean.FALSE::equals)){
            failWithMessage("全てNULLではありません", actual);
        }
        return this;
    }
}
