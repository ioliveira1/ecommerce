package com.ioliveira.ecommerce.services;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String filePath) {
        try {
            LOG.info("Iniciando upload");
            amazonS3.putObject(bucketName, "File.jpeg", new File(filePath));
            LOG.info("Upload finalizado");
        } catch (Exception e) {
            LOG.info("Erro ao fazer upload do arquivo!");
            LOG.info("Exception: " + e.getClass().toString());
            LOG.info("Message: " + e.getMessage());
        }
    }

}
