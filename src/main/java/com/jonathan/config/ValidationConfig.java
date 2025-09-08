package com.jonathan.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ValidationConfig {

    @Value("${app.email.regex}")
    private String emailRegex;

    @Value("${app.password.regex}")
    private String passwordRegex;
}