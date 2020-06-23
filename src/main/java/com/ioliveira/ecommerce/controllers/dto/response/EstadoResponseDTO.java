package com.ioliveira.ecommerce.controllers.dto.response;

import com.ioliveira.ecommerce.entities.Estado;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class EstadoResponseDTO {
    private Integer id;
    private String nome;

    public EstadoResponseDTO (Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

}
