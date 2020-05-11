package com.ioliveira.ecommerce.services.email;

import com.ioliveira.ecommerce.entities.Pedido;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public interface EmailService {
    void sendEmail(Pedido pedido);

    default SimpleMailMessage prepareMessageFromPedido(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setSubject("Pedido " + pedido.getId());
        message.setSentDate(new Date());
        message.setText(pedido.toString());
        return message;
    }
}
