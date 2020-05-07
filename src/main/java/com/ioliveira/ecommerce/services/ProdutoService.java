package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.entities.Produto;
import com.ioliveira.ecommerce.repositories.CategoriaRepository;
import com.ioliveira.ecommerce.repositories.ProdutoRepository;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto findById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public Page<Produto> findPage(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categoriaList = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categoriaList, pageRequest);
    }

}
