package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Cliente;
import com.ioliveira.ecommerce.repositories.ClienteRepository;
import com.ioliveira.ecommerce.services.email.EmailService;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import com.ioliveira.ecommerce.services.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailService emailService;

    public void sendNewPassword(String email) {
        final Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado!");
        }

        final String newPassword = PasswordUtils.newPassword();
        cliente.setSenha(encoder.encode(newPassword));

        clienteRepository.save(cliente);
        emailService.sendNewPassword(cliente, newPassword);
    }

}
