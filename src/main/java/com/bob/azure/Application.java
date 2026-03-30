package com.bob.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bob.azure.service.QueueService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = {"com.bob.azure"})
public class Application implements CommandLineRunner {

    @Autowired
    private QueueService queueService;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        var messages = queueService.receiveMessages(32);
        messages.forEach(m -> {
            log.info("Received message: {}", new String(m.getBody().toBytes())); 
        });
    }
}
