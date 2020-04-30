package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
        Categoria insert = categoriaService.insert(categoria);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insert.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
