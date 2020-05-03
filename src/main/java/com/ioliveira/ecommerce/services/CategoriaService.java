package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.controllers.dto.request.CategoriaRequestDTO;
import com.ioliveira.ecommerce.controllers.dto.response.CategoriaResponseDTO;
import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.repositories.CategoriaRepository;
import com.ioliveira.ecommerce.services.exceptions.DataIntegrityException;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> findAll() {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        return categoriaList
                .stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public Page<CategoriaResponseDTO> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest).map(CategoriaResponseDTO::new);
    }

    public Categoria insert(CategoriaRequestDTO requestDTO) {
        Categoria categoria = convertToEntity(requestDTO);
        return categoriaRepository.save(categoria);
    }

    public void update(Integer id, CategoriaRequestDTO requestDTO) {
        Categoria categoriaDB = findById(id);
        categoriaUpdated(categoriaDB, requestDTO.getNome());
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar uma Categoria que possui produtos associados.");
        }
    }

    private void categoriaUpdated(Categoria categoria, String nomeCategoria) {
        categoria.setNome(nomeCategoria);
        categoriaRepository.save(categoria);
    }

    private Categoria convertToEntity(CategoriaRequestDTO requestDTO) {
        return new Categoria(requestDTO.getNome());
    }

}
