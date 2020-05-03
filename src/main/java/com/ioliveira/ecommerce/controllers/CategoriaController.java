package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.controllers.dto.request.CategoriaRequestDTO;
import com.ioliveira.ecommerce.controllers.dto.response.CategoriaResponseDTO;
import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<CategoriaResponseDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                               @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                               @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        return ResponseEntity.ok().body(categoriaService.findPage(page, linesPerPage, direction, orderBy));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaRequestDTO requestDTO) {
        Categoria insert = categoriaService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insert.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody CategoriaRequestDTO requestDTO) {
        categoriaService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
