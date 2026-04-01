package com.bob.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bob.azure.service.QueueService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.bob.azure"})
public class Application {

    @Autowired
    private QueueService queueService;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(fixedDelay = 30000)
    public void readMessages() {
        log.info("Reading messages from queue...");
        var messages = queueService.receiveMessages(32);
        messages.forEach(m -> {
            log.info("Received message: {}", new String(m.getBody().toBytes())); 
        });
        log.info("Completed reading messages from queue...");
    }
}
