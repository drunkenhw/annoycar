package com.anonycar.member.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorConfig {

    @Bean
    public Encryptor encryptor() {
        return new EncryptorImpl();
    }
}
