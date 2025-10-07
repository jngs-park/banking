// src/main/java/com/example/demo/controller/ArticleController.java
package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleEventService;
import com.example.demo.service.RedisCacheService;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
    public class ArticleController {
        private final ArticleRepository articleRepository;
        private final ArticleEventService articleEventService;
        private final RedisCacheService redisCacheService;

        public ArticleController(ArticleRepository articleRepository,
                                ArticleEventService articleEventService,
                                RedisCacheService redisCacheService) {
            this.articleRepository = articleRepository;
            this.articleEventService = articleEventService;
            this.redisCacheService = redisCacheService;
    }


    @PostMapping
    public Map<String, Object> create(@RequestBody Article article) {
        Article saved = articleRepository.save(article);

        // Kafka & Redis 이벤트 발행
        articleEventService.sendArticleEvent(saved.getTitle());
        redisCacheService.saveArticle("lastArticle", saved.getTitle());

        // ✅ JSON 직렬화 문제를 우회하기 위해 DTO(Map) 형태로 리턴
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("title", saved.getTitle());
        response.put("content", saved.getContent());
        return response;
    }

}