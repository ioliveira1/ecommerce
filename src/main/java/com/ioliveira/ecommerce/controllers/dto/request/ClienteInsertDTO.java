package com.ioliveira.ecommerce.controllers.dto.request;

import com.ioliveira.ecommerce.controllers.dto.validations.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ClienteInsert
public class ClienteInsertDTO {
    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório.")
    private String cpfCnpj;

    @NotNull
    private Integer tipoCliente;

    @NotEmpty(message = "Preenchimento obrigatório.")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório.")
    private String numero;
    private String complemento;

    @NotEmpty(message = "Preenchimento obrigatório.")
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório.")
    private String cep;

    @Size(min = 1, message = "Pelo menos um telefone deve ser cadastrado.")
    private Set<String> telefones = new HashSet<>();

    @NotNull
    private Integer cidadeId;
}
