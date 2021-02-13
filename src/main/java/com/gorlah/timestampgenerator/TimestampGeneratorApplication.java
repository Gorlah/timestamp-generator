package com.gorlah.timestampgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@FunctionalInterface
interface TimestampGenerator {

    long generateTimestamp();
}

@SpringBootApplication
public class TimestampGeneratorApplication {

    private static final Logger logger = LoggerFactory.getLogger(TimestampGeneratorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TimestampGeneratorApplication.class, args);
    }

    @Bean
    TimestampGenerator timestampGenerator() {
        return System::currentTimeMillis;
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(TimestampGenerator timestampGenerator) {
        return (applicationReadyEvent) -> logger.info("Happy %tA!".formatted(timestampGenerator.generateTimestamp()));
    }
}
