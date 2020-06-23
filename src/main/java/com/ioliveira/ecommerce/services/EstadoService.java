package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Estado;
import com.ioliveira.ecommerce.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAllByOrderByNome() {
        return estadoRepository.findAllByOrderByNome();
    }

}
