package com.ioliveira.ecommerce.controllers.dto;

import com.ioliveira.ecommerce.entities.Categoria;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class CategoriaDTO implements Serializable {
    private Integer id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
