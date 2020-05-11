package com.ioliveira.ecommerce.config.profiles.dev;

import com.ioliveira.ecommerce.services.DBService;
import com.ioliveira.ecommerce.services.email.EmailService;
import com.ioliveira.ecommerce.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDataBase() {
        if (strategy.equals("create")) {
            dbService.instantiateTestDataBase();
        }
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
