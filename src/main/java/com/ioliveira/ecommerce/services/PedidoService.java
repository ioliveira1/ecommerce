package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.config.security.authentication.UserSS;
import com.ioliveira.ecommerce.entities.Cliente;
import com.ioliveira.ecommerce.entities.PagamentoBoleto;
import com.ioliveira.ecommerce.entities.Pedido;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;
import com.ioliveira.ecommerce.repositories.ItemPedidoRepository;
import com.ioliveira.ecommerce.repositories.PagamentoRepository;
import com.ioliveira.ecommerce.repositories.PedidoRepository;
import com.ioliveira.ecommerce.services.email.EmailService;
import com.ioliveira.ecommerce.services.exceptions.AuthorizationException;
import com.ioliveira.ecommerce.services.exceptions.ObjectNotFoundException;
import com.ioliveira.ecommerce.services.utils.BoletoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
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
            ped.setProduto(produtoService.findById(ped.getProduto().getId()));
            ped.setPreco(ped.getProduto().getPreco());
            ped.setPedido(pedidoSaved);
        });

        itemPedidoRepository.saveAll(pedidoSaved.getItens());

        emailService.sendEmail(pedidoSaved);

        return pedidoSaved;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        final UserSS userAuthenticated = UserService.userAuthenticated();
        if (userAuthenticated == null) {
            throw new AuthorizationException("Acesso negado!");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        final Cliente cliente = clienteService.findById(userAuthenticated.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}
