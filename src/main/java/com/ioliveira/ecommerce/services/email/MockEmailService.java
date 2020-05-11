package com.ioliveira.ecommerce.services.email;

import com.ioliveira.ecommerce.entities.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendEmail(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareMessageFromPedido(pedido);
        mailMessage.setFrom(sender);
        LOG.info("Simulando envio de email");
        LOG.info(mailMessage.toString());
        LOG.info("Email enviado");
    }
}
