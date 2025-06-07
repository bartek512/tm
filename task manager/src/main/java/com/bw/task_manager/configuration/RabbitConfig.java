package com.bw.task_manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "task.queue";

    @Bean
    public Queue queue() {
//        return new Queue(QUEUE_NAME, false);
        return null;
    }
}
