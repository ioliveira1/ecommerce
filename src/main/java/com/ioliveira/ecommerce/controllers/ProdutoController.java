package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.controllers.dto.response.ProdutoResponseDTO;
import com.ioliveira.ecommerce.controllers.utils.URL;
import com.ioliveira.ecommerce.entities.Produto;
import com.ioliveira.ecommerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<ProdutoResponseDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "1") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        Page<Produto> produtos = produtoService.findPage(URL.decodeCaracters(nome),
                URL.convertParamsToList(categorias), page, linesPerPage, direction, orderBy);
        Page<ProdutoResponseDTO> responseDTOS = produtos.map(ProdutoResponseDTO::new);
        return ResponseEntity.ok().body(responseDTOS);
    }

}
