package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.entities.Pedido;
import com.ioliveira.ecommerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
        Pedido pedidoDB = pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedidoDB.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
