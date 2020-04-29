package com.ioliveira.ecommerce.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode(of = {"pedido", "produto"})
@Embeddable
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
