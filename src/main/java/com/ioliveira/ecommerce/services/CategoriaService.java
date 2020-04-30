package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.Categoria;
import com.ioliveira.ecommerce.repositories.CategoriaRepository;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public Categoria insert(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void update(Categoria categoria) {
        Categoria categoriaDB = findById(categoria.getId());
        categoriaUpdated(categoriaDB, categoria.getNome());
    }

    private void categoriaUpdated(Categoria categoria, String nomeCategoria) {
        categoria.setNome(nomeCategoria);
        categoriaRepository.save(categoria);
    }

}
