package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Pedido;
import com.ioliveira.ecommerce.repositories.PedidoRepository;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

}
