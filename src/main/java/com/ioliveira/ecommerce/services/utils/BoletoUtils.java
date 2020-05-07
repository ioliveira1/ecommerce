package com.ioliveira.ecommerce.services.utils;

import com.ioliveira.ecommerce.entities.PagamentoBoleto;

import java.time.LocalDateTime;

public class BoletoUtils {

    public static void preencheVencimento(PagamentoBoleto boleto, LocalDateTime instantePedido){
        boleto.setDataVencimento(instantePedido.plusDays(7));
    }

}
