package com.kolaykafe.userproducer.config;

import com.kolaykafe.userproducer.dto.UserDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
    @Bean
    public NewTopic userServiceTopic(String action, UserDTO obj) {
        return TopicBuilder.name("userServiceTopic").build();
    }
}