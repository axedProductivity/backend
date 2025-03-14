package com.axedBackend.AxedBackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoggingTestRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(LoggingTestRunner.class);

    @Override
    public void run(String... args) {
        // Test different logging levels
        System.out.println("DIRECT SYSTEM OUT: This is a direct System.out.println test");
        logger.debug("DEBUG LOG: This is a debug message");
        logger.info("INFO LOG: This is an info message");
        logger.warn("WARN LOG: This is a warning message");
        logger.error("ERROR LOG: This is an error message");

        // Test exception logging
        try {
            throw new RuntimeException("Test exception");
        } catch (Exception e) {
            logger.error("EXCEPTION LOG: Exception occurred", e);
        }
    }
}