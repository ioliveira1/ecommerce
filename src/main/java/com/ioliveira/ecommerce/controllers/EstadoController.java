package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.controllers.dto.response.CidadeResponseDTO;
import com.ioliveira.ecommerce.controllers.dto.response.EstadoResponseDTO;
import com.ioliveira.ecommerce.entities.Estado;
import com.ioliveira.ecommerce.services.CidadeService;
import com.ioliveira.ecommerce.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoResponseDTO>> findAllByOrderByNome() {
        List<EstadoResponseDTO> estadoDTOList = estadoService
                .findAllByOrderByNome()
                .stream()
                .map(EstadoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(estadoDTOList);
    }

    @GetMapping(path = "/{estadoId}/cidades")
    public ResponseEntity<List<CidadeResponseDTO>> findByEstadoId(@PathVariable Integer estadoId) {
        List<CidadeResponseDTO> cidadeDTOList = cidadeService
                .findByEstadoId(estadoId)
                .stream()
                .map(CidadeResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cidadeDTOList);
    }

}
