package com.ioliveira.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
//Campo adcional da classe enviado por JSON na anotacao da superclasse no campo @type
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento implements Serializable {

    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;

    public PagamentoBoleto() {
    }

    public PagamentoBoleto(EstadoPagamento estadoPagamento, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
        super(estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
