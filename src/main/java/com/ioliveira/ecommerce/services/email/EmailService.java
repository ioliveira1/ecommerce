package com.ioliveira.ecommerce.services.email;

import com.ioliveira.ecommerce.entities.Cliente;
import com.ioliveira.ecommerce.entities.Pedido;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public interface EmailService {
    void sendEmail(Pedido pedido);

    void sendNewPassword(Cliente cliente, String newPassword);

    default SimpleMailMessage prepareMessageFromPedido(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setSubject("Pedido " + pedido.getId());
        message.setSentDate(new Date());
        message.setText(pedido.toString());
        return message;
    }

    default SimpleMailMessage prepareNewPassword(Cliente cliente, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cliente.getEmail());
        message.setSubject("Solicitação de nova senha");
        message.setSentDate(new Date());
        message.setText("Nova senha: " + newPassword);
        return message;
    }
}
