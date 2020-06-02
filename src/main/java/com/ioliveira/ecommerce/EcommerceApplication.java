package com.ioliveira.ecommerce;

import com.ioliveira.ecommerce.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        s3Service.uploadFile("D:\\Desenvolvimento\\ap_19.jpeg");
    }
}
