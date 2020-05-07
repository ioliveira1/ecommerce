package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.PagamentoBoleto;
import com.ioliveira.ecommerce.entities.Pedido;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;
import com.ioliveira.ecommerce.repositories.ItemPedidoRepository;
import com.ioliveira.ecommerce.repositories.PagamentoRepository;
import com.ioliveira.ecommerce.repositories.PedidoRepository;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import com.ioliveira.ecommerce.services.utils.BoletoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setData(LocalDateTime.now());
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoBoleto) {
            PagamentoBoleto pagamento = (PagamentoBoleto) pedido.getPagamento();
            BoletoUtils.preencheVencimento(pagamento, pedido.getData());
        }
        Pedido pedidoSaved = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        pedidoSaved.getItens().forEach(ped -> {
            ped.setDesconto(0.0);
            ped.setPreco(produtoService.findById(ped.getProduto().getId()).getPreco());
            ped.setPedido(pedidoSaved);
        });

        itemPedidoRepository.saveAll(pedidoSaved.getItens());

        return pedidoSaved;
    }

}
