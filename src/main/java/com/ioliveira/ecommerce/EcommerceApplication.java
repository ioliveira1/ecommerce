package com.ioliveira.ecommerce;

import com.ioliveira.ecommerce.entities.*;
import com.ioliveira.ecommerce.entities.enums.EstadoPagamento;
import com.ioliveira.ecommerce.entities.enums.TipoCliente;
import com.ioliveira.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

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

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritóirio");
        Categoria cat3 = new Categoria("Cama mesa e banho");
        Categoria cat4 = new Categoria("Eletrônicos");
        Categoria cat5 = new Categoria("Jardinagem");
        Categoria cat6 = new Categoria("Decoração");
        Categoria cat7 = new Categoria("Perfumaria");
        Categoria cat8 = new Categoria("Categoria 8");
        Categoria cat9 = new Categoria("Categoria 9");
        Categoria cat10 = new Categoria("Categoria 10");
        Categoria cat11 = new Categoria("Categoria 11");
        Categoria cat12 = new Categoria("Categoria 12");

        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Collections.singletonList(p2));

        p1.getCategorias().addAll(Collections.singletonList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Collections.singletonList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlandia", est1);
        Cidade c2 = new Cidade("São Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "11111111111", TipoCliente.PESSOA_FISICA);
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
