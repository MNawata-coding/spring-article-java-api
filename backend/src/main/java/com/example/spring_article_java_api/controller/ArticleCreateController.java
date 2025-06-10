package com.example.spring_article_java_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_article_java_api.dto.request.ArticleDetailCreateRequestDto;
import com.example.spring_article_java_api.dto.response.ArticleDetailCreateResponseDto;
import com.example.spring_article_java_api.service.create.ArticleCreateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("articles")
public class ArticleCreateController {

    ArticleCreateService service;

    public ArticleCreateController(ArticleCreateService service){
        this.service = service;
    }

    /***
     * 記事保存処理
     * @param dto
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<ArticleDetailCreateResponseDto>  articleSave(@Valid @RequestBody ArticleDetailCreateRequestDto dto){
        ArticleDetailCreateResponseDto result = service.createArticle(dto);
        return ResponseEntity.ok(result);
    }
}
 