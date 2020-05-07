package com.ioliveira.ecommerce.controllers.dto.response;

import com.ioliveira.ecommerce.entities.Produto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ProdutoResponseDTO {
    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
