package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = "demo-topic")
    public void listen(String message) {
        log.info("Received: {}", message);
    }
}
