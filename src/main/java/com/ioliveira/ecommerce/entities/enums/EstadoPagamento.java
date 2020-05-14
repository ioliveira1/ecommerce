package com.ioliveira.ecommerce.entities.enums;

import java.util.Arrays;

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
        if (codigo != null) {
            return Arrays.stream(EstadoPagamento.values())
                    .filter(perfil -> codigo.equals(perfil.getCodigo()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("EstadoPagamento codigo " + codigo + " não encontrado!"));
        }
        return null;
    }

}
