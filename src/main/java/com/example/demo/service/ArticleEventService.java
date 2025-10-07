package com.example.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
    public class ArticleEventService {

        private final KafkaTemplate<String, String> kafkaTemplate;

        public ArticleEventService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

        public void sendArticleEvent(String message) {
        kafkaTemplate.send("article-events", message);
        System.out.println("✅ Kafka 메시지 전송됨: " + message);
    }
}
