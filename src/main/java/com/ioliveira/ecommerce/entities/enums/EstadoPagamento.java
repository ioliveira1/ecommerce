package com.ioliveira.ecommerce.entities.enums;

public enum EstadoPagamento {
    PENDENTE(1,"Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private final int codigo;
    private final String descricao;

    EstadoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer codigo){
        if (codigo != null){
            for (EstadoPagamento estadoPagamento : EstadoPagamento.values()){
                if (codigo.equals(estadoPagamento.getCodigo())){
                    return estadoPagamento;
                }
            }
            throw new IllegalArgumentException("EstadoPagamento codigo " + codigo + " não encontrado!");
        }
        return null;

    }

}
