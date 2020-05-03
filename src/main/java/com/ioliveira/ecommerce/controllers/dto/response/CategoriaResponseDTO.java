package com.ioliveira.ecommerce.controllers.dto.response;

import com.ioliveira.ecommerce.entities.Categoria;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class CategoriaResponseDTO implements Serializable {
    private Integer id;
    private String nome;

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
