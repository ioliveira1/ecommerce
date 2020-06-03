package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
        final String extension = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
            try {
                BufferedImage image = ImageIO.read(uploadFile.getInputStream());
                if (extension.equals("png")) {
                    image = pngToJpg(image);
                }
                return image;
            } catch (IOException e) {
                throw new FileException("Erro ao ler arquivo.");
            }
        }
        throw new FileException("Somente imagens PNG e JPG são permitidas.");
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.white, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extension) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, extension, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo.");
        }
    }

    public BufferedImage cropSquare(BufferedImage image) {
        int min = image.getWidth();
        if (image.getHeight() <= image.getWidth()) {
            min = image.getHeight();
        }

        return Scalr.crop(image,
                (image.getWidth() / 2) - (min / 2),
                (image.getHeight() / 2) - (min / 2),
                min,
                min);

    }

    public BufferedImage resize(BufferedImage image, int size) {
        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
    }

}
