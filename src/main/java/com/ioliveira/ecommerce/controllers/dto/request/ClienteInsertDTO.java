package com.ioliveira.ecommerce.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ClienteInsertDTO {
    private String nome;
    private String email;
    private String cpfCnpj;
    private Integer tipoCliente;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Set<String> telefones = new HashSet<>();
    private Integer cidadeId;
}
