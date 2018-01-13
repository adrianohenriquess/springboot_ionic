package br.com.inovacenter.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.inovacenter.cursomc.entity.Categoria;
import br.com.inovacenter.cursomc.entity.Cidade;
import br.com.inovacenter.cursomc.entity.Cliente;
import br.com.inovacenter.cursomc.entity.Endereco;
import br.com.inovacenter.cursomc.entity.Estado;
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
import br.com.inovacenter.cursomc.repositories.PagamentoRepository;
import br.com.inovacenter.cursomc.repositories.PedidoRepository;
import br.com.inovacenter.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Informática");
		Categoria categoria2 = new Categoria();
		categoria2.setNome("Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().add(produto2);
		
		produto1.getCategorias().add(categoria1);
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().add(categoria1);
				
		categoriaRepository.save(Arrays.asList(categoria1, categoria2));
		produtoRepository.save(Arrays.asList(produto1, produto2, produto3));
		
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
	}
}
