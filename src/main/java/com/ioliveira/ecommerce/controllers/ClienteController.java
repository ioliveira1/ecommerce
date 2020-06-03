package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.controllers.dto.request.ClienteInsertDTO;
import com.ioliveira.ecommerce.controllers.dto.request.ClienteRequestDTO;
import com.ioliveira.ecommerce.controllers.dto.response.ClienteResponseDTO;
import com.ioliveira.ecommerce.entities.Cliente;
import com.ioliveira.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<ClienteResponseDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        return ResponseEntity.ok().body(clienteService.findPage(page, linesPerPage, direction, orderBy));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ClienteRequestDTO requestDTO) {
        clienteService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteInsertDTO insertDTO) {
        Cliente insert = clienteService.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insert.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        return ResponseEntity.created(clienteService.uploadProfilePicture(file)).build();
    }

}
