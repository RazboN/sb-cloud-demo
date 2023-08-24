package com.kolaykafe.admin.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public NewTopic getAll() {
        return new NewTopic("ADD_MENU", 3, (short) 1);
    }
}
