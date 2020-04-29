package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.entities.Pedido;
import com.ioliveira.ecommerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }

}
