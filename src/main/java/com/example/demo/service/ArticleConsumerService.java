package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ArticleConsumerService {

    @KafkaListener(topics = "article-events", groupId = "article-group")
    public void listen(String message) {
        System.out.println("📩 Kafka 메시지 수신됨: " + message);
    }
}
