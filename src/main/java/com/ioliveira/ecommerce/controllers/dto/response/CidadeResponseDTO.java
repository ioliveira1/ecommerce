package com.ioliveira.ecommerce.controllers.dto.response;

import com.ioliveira.ecommerce.entities.Cidade;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class CidadeResponseDTO {
    private Integer id;
    private String nome;

    public CidadeResponseDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }
}
