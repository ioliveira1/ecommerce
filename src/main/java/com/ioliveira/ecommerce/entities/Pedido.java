package com.ioliveira.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = {"id"})
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(LocalDateTime date, Cliente cliente, Endereco enderecoEntrega) {
        this.data = date;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }

    public Integer getId() {
        return id;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }
}
