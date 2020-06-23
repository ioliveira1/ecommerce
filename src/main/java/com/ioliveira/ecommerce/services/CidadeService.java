package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Cidade;
import com.ioliveira.ecommerce.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstadoId(Integer estadoId) {
        List<Cidade> byEstadoId = cidadeRepository.findByEstadoId(estadoId);
        return byEstadoId;
    }

}
