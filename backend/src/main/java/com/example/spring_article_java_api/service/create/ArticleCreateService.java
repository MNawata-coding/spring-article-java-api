package com.example.spring_article_java_api.service.create;

import com.example.spring_article_java_api.dto.request.ArticleDetailCreateRequestDto;
import com.example.spring_article_java_api.dto.response.ArticleDetailCreateResponseDto;

/***
 * 記事クラスのインターフェース
 * @author nawata
 * @version 1.0.0
 */
public interface ArticleCreateService {
    /***
     * 記事登録用メソッド
     * @param dto
     */
    ArticleDetailCreateResponseDto createArticle(ArticleDetailCreateRequestDto dto);

    // /***
    //  * 新着記事一覧
    //  * @return　List<Article>
    //  */
    // List<ArticleInfoResponseDto> getNewArticleList();

    // /***
    //  * ユーザーの記事一覧取得
    //  * @param userId
    //  * @return
    //  */
    // List<ArticleInfoResponseDto> getUserArticleList(String userId);
    
    // /***
    //  * 投稿フラグ(投稿)
    //  * @param articleId
    //  */
    // ResultDto postArticle(Long articleId);

    // /***
    //  * 投稿フラグ(取り消し)
    //  * @param articleId
    //  */
    // ResultDto cancelPostArticle(Long articleId);

    // /***
    //  * 記事更新用メソッド
    //  * @param dto
    //  */
    // ResultDto updateArticle(ArticleDetailUpdateRequestDto dto);

    // /***
    //  * 記事削除用メソッド
    //  * @param userId
    //  */
    // ResultDto deleteArticle(Long userId, Long articleId);
}
