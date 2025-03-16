package com.main.fakeMessenger.base.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class ErrorMessageService {
    private final Properties errorMessages = new Properties();

    public ErrorMessageService() {
        try {
            try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("error-messages.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find error-messages.properties");
                }

                this.errorMessages.load(input);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error loading error-messages.properties", e);
        }
    }

    public String getMessage(String key) {
        return this.errorMessages.getProperty(key, "Bad Request");
    }

    public String getCode(String key) {
        return this.errorMessages.getProperty(key, "000");
    }
}