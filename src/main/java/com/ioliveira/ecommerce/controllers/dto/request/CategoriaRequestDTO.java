package com.ioliveira.ecommerce.controllers.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class CategoriaRequestDTO {
    @NotEmpty(message = "Preenchimento obrigatório.")
    @Size(min = 4, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;
}
