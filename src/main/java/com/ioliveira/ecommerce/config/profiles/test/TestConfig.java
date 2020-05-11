package com.ioliveira.ecommerce.config.profiles.test;

import com.ioliveira.ecommerce.services.DBService;
import com.ioliveira.ecommerce.services.email.EmailService;
import com.ioliveira.ecommerce.services.email.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;


    @Bean
    public boolean instantiateDataBase() {
        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
