package br.com.inovacenter.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inovacenter.cursomc.entity.Categoria;
import br.com.inovacenter.cursomc.entity.Cidade;
import br.com.inovacenter.cursomc.entity.Cliente;
import br.com.inovacenter.cursomc.entity.Endereco;
import br.com.inovacenter.cursomc.entity.Estado;
import br.com.inovacenter.cursomc.entity.ItemPedido;
import br.com.inovacenter.cursomc.entity.Pagamento;
import br.com.inovacenter.cursomc.entity.PagamentoComBoleto;
import br.com.inovacenter.cursomc.entity.PagamentoComCartao;
import br.com.inovacenter.cursomc.entity.Pedido;
import br.com.inovacenter.cursomc.entity.Produto;
import br.com.inovacenter.cursomc.entity.enums.EstadoPagamento;
import br.com.inovacenter.cursomc.entity.enums.TipoCliente;
import br.com.inovacenter.cursomc.repositories.CategoriaRepository;
import br.com.inovacenter.cursomc.repositories.CidadeRepository;
import br.com.inovacenter.cursomc.repositories.ClienteRepository;
import br.com.inovacenter.cursomc.repositories.EnderecoRepository;
import br.com.inovacenter.cursomc.repositories.EstadoRepository;
import br.com.inovacenter.cursomc.repositories.ItemPedidoRepository;
import br.com.inovacenter.cursomc.repositories.PagamentoRepository;
import br.com.inovacenter.cursomc.repositories.PedidoRepository;
import br.com.inovacenter.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedioRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Cama, mesa e banho");
		Categoria categoria4 = new Categoria(null, "Eletrônicos");
		Categoria categoria5 = new Categoria(null, "Jardinagem");
		Categoria categoria6 = new Categoria(null, "Decoração");
		Categoria categoria7 = new Categoria(null, "Perfumaria");
		
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		Produto produto4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto produto5 = new Produto(null, "Toalha", 50.00);
		Produto produto6 = new Produto(null, "Colcha", 200.00);
		Produto produto7 = new Produto(null, "TV true Color", 1200.00);
		Produto produto8 = new Produto(null, "Roçadeira", 800.00);
		Produto produto9 = new Produto(null, "Abajour", 100.00);
		Produto produto10 = new Produto(null, "Pendente", 180.00);
		Produto produto11 = new Produto(null, "Shampoo", 90.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5, produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9, produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));
		
		categoriaRepository.save(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
		produtoRepository.save(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7,
												produto8, produto9, produto10, produto11));
		
		Estado estado1 = new Estado(null, "Mina Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo",  estado2);
		Cidade cidade3 = new Cidade(null, "Campinas",   estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.save(Arrays.asList(estado1, estado2));
		cidadeRepository.save(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "43555534455", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("543254325342", "42543254325423"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "casa", "Centro", "13295-000", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua Mattos", "302", "casa", "Centro", "13295-000", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.save(cliente1);
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 10:45"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 12);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 10:32"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.save(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.save(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido item1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.0);
		ItemPedido item2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(item1, item2));
		pedido1.getItens().addAll(Arrays.asList(item3));
		
		produto1.getItens().add(item1);
		produto2.getItens().add(item3);
		produto3.getItens().add(item2);
		
		itemPedioRepository.save(Arrays.asList(item1, item2, item3));
	}
}
