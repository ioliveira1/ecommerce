package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.controllers.dto.CategoriaDTO;
import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy){
        return ResponseEntity.ok().body(categoriaService.findPage(page, linesPerPage, direction, orderBy));
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

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Categoria categoria) {
        categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
