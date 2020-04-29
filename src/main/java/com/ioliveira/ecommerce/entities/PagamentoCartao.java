package com.ioliveira.ecommerce.entities;

import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class PagamentoCartao extends Pagamento implements Serializable {

    private int numeroParcelas;

    public PagamentoCartao() {
    }

    public PagamentoCartao(EstadoPagamento estadoPagamento, Pedido pedido, int numeroParcelas) {
        super(estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
