package com.example.spring_article_java_api.service.create;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.spring_article_java_api.dto.request.ArticleDetailCreateRequestDto;
import com.example.spring_article_java_api.dto.response.ArticleDetailResponseDto;
import com.example.spring_article_java_api.entity.Article;
import com.example.spring_article_java_api.exception.ArticleCreateException;
import com.example.spring_article_java_api.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ArticleCreateServiceImpl implements ArticleCreateService {

    ArticleRepository repository;

    @Transactional
    public ArticleDetailResponseDto createArticle(ArticleDetailCreateRequestDto dto) throws ArticleCreateException{

        //Entityに登録する値を設定する
        Article article = new Article(dto.getTitle(), dto.getUserId(), dto.getContent(), dto.isReleaseFlg(),dto.getUserId());
        //設定した値で登録する
        try {
            Article result = repository.save(article);
            ArticleDetailResponseDto responseDto = ArticleDetailResponseDto.builder()
                                        .title(result.getTitle())
                                        .content(result.getContent())
                                        .releaseFlg(result.isReleaseFlg())
                                        .createdAt(result.getCreatedAt())
                                        .updatedAt(result.getUpdatedAt())
                                        .build();
            //保存処理
            return responseDto;
        } catch (IllegalArgumentException e) {
            //DBでエラーが発生した場合
            log.error(e.getMessage());
            throw new ArticleCreateException("DB登録エラー");
        } catch (OptimisticLockingFailureException e) {
            //DBロックエラー
            log.error(e.getMessage());
            throw new ArticleCreateException("DBロックエラー");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ArticleCreateException("不明なエラーが発生しました");
        }
    }
}
