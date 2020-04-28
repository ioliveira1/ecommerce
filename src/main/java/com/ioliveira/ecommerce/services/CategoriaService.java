package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

}
