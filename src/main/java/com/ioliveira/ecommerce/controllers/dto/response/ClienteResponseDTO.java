package com.ioliveira.ecommerce.controllers.dto.response;

import com.ioliveira.ecommerce.entities.Cliente;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ClienteResponseDTO {
    private Integer id;
    private String nome;
    private String email;
    private String cpfCnpj;
    private Integer tipoCliente;

    public ClienteResponseDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpfCnpj = cliente.getCpfCnpj();
        this.tipoCliente = cliente.getTipoCliente().getCodigo();
    }
}
