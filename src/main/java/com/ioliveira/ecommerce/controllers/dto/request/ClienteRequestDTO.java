package com.ioliveira.ecommerce.controllers.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ClienteRequestDTO {
    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 4, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Email(message = "Email inválido.")
    private String email;
}
