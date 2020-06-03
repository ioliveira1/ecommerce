package com.ioliveira.ecommerce.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ioliveira.ecommerce.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            final InputStream inputStream = multipartFile.getInputStream();
            final String filename = multipartFile.getOriginalFilename();
            final String contentType = multipartFile.getContentType();
            return uploadFile(inputStream, filename, contentType);
        } catch (IOException e) {
            throw new RuntimeException("Erro IO: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            LOG.info("Iniciando upload");
            amazonS3.putObject(bucketName, fileName, inputStream, metadata);
            LOG.info("Upload finalizado");
            return amazonS3.getUrl(bucketName, fileName).toURI();
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
    }

}
