package com.ioliveira.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioliveira.ecommerce.entities.PagamentoBoleto;
import com.ioliveira.ecommerce.entities.PagamentoCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//Classe de configuracao que registra as subclasses PagamentoBoleto e PagamentoCartao
//Sera configurada no inicio da execucao da aplicacao
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoBoleto.class);
                objectMapper.registerSubtypes(PagamentoCartao.class);
                super.configure(objectMapper);
            }
        };
    }
}
