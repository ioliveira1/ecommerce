package com.ioliveira.ecommerce.repositories;

import com.ioliveira.ecommerce.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
