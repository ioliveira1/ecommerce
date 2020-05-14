package com.ioliveira.ecommerce.entities.enums;

import java.util.Arrays;

public enum TipoCliente {
    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private final int codigo;
    private final String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo) {
        if (codigo != null) {
            return Arrays.stream(TipoCliente.values())
                    .filter(perfil -> codigo.equals(perfil.getCodigo()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("TipoCliente codigo " + codigo + " não encontrado!"));
        }
        return null;
    }

}
