package com.ioliveira.ecommerce.services.email;

import com.ioliveira.ecommerce.entities.Cliente;
import com.ioliveira.ecommerce.entities.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareMessageFromPedido(pedido);
        mailMessage.setFrom(sender);
        LOG.info("Enviando email");
        mailSender.send(mailMessage);
        LOG.info("Email enviado");
    }

    @Override
    public void sendNewPassword(Cliente cliente, String newPassword) {
        SimpleMailMessage mailMessage = prepareNewPassword(cliente, newPassword);
        mailMessage.setFrom(sender);
        LOG.info("Enviando nova senha");
        mailSender.send(mailMessage);
        LOG.info("Email enviado");
    }

}
