package com.ioliveira.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(of = {"id"})
@Entity
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private final ItemPedidoPK id = new ItemPedidoPK();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido (Pedido pedido){
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto (Produto produto){
        id.setProduto(produto);
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public double getSubtotal(){
        return (preco - desconto) * quantidade;
    }
}
