package com.ioliveira.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(of = {"id"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//Permite a instanciacao de subclasses a partir de dados JSON
//A classe Pagamento tem um campo adicional chamado "@type"
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public abstract class Pagamento implements Serializable {

    @Id
    private Integer id;
    private Integer estadoPagamento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(EstadoPagamento estadoPagamento, Pedido pedido) {
        this.estadoPagamento = estadoPagamento.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(estadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
