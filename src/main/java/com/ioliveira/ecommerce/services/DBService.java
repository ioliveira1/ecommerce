package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.entities.*;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;
import com.ioliveira.ecommerce.entities.enums.TipoCliente;
import com.ioliveira.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void instantiateTestDataBase() {

        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritóirio");
        Categoria cat3 = new Categoria("Cama mesa e banho");
        Categoria cat4 = new Categoria("Eletrônicos");
        Categoria cat5 = new Categoria("Jardinagem");
        Categoria cat6 = new Categoria("Decoração");
        Categoria cat7 = new Categoria("Perfumaria");

        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);
        Produto p4 = new Produto("Mesa de escritório", 300.00);
        Produto p5 = new Produto("Toalha", 50.00);
        Produto p6 = new Produto("Colcha", 200.00);
        Produto p7 = new Produto("TV true color", 1200.00);
        Produto p8 = new Produto("Roçadeira", 800.00);
        Produto p9 = new Produto("Abajour", 100.00);
        Produto p10 = new Produto("Pendente", 180.00);
        Produto p11 = new Produto("Shampoo", 90.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Collections.singletonList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Collections.singletonList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Collections.singletonList(cat2));
        p5.getCategorias().addAll(Collections.singletonList(cat3));
        p6.getCategorias().addAll(Collections.singletonList(cat3));
        p7.getCategorias().addAll(Collections.singletonList(cat4));
        p8.getCategorias().addAll(Collections.singletonList(cat5));
        p9.getCategorias().addAll(Collections.singletonList(cat6));
        p10.getCategorias().addAll(Collections.singletonList(cat6));
        p11.getCategorias().addAll(Collections.singletonList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlandia", est1);
        Cidade c2 = new Cidade("São Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente("Maria Silva", "igorfoliveira@gmail.com", "11111111111", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("22334455", "911112222"));

        Endereco e1 = new Endereco("Rua Flores", "300", "Apto 203", "Jardim", "03355010", cli1, c1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "08789010", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        Pedido ped1 = new Pedido(LocalDateTime.of(2017, 9, 30, 10, 32), cli1, e1);
        Pedido ped2 = new Pedido(LocalDateTime.of(2017, 10, 10, 19, 35), cli1, e2);

        Pagamento pagto1 = new PagamentoCartao(EstadoPagamento.QUITADO, ped1, 6);
        Pagamento pagto2 = new PagamentoBoleto(EstadoPagamento.PENDENTE, ped2,
                LocalDateTime.of(2017, 10, 20, 0, 0), null);

        ped1.setPagamento(pagto1);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

}
