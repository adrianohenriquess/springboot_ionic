package br.com.inovacenter.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inovacenter.cursomc.entity.ItemPedido;
import br.com.inovacenter.cursomc.entity.PagamentoComBoleto;
import br.com.inovacenter.cursomc.entity.Pedido;
import br.com.inovacenter.cursomc.entity.enums.EstadoPagamento;
import br.com.inovacenter.cursomc.repositories.ItemPedidoRepository;
import br.com.inovacenter.cursomc.repositories.PagamentoRepository;
import br.com.inovacenter.cursomc.repositories.PedidoRepository;
import br.com.inovacenter.cursomc.repositories.ProdutoRepository;
import br.com.inovacenter.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setPedido(pedido);
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(produtoRepository.findOne(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.save(pedido.getItens());
		return pedido;
	}
}
