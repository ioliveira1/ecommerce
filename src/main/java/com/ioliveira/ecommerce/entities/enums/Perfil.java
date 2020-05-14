package com.ioliveira.ecommerce.entities.enums;

import java.util.Arrays;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int codigo;
    private String descricao;

    Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer codigo) {
        if (codigo != null) {
            return Arrays.stream(Perfil.values())
                    .filter(perfil -> codigo.equals(perfil.getCodigo()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Perfil codigo " + codigo + " não encontrado!"));
        }
        return null;
    }
}
