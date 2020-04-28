package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){

        List<Categoria> categoriaList =
                Arrays.asList(new Categoria(10, "Cat 1"), new Categoria(20, "Cat 2"));

        return ResponseEntity.ok().body(categoriaList);

    }

}
