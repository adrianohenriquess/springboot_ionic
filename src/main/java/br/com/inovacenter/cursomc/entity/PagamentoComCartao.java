package br.com.inovacenter.cursomc.entity;

import javax.persistence.Entity;

import br.com.inovacenter.cursomc.entity.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 5407671700075749257L;
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}
	
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroParcelas;
	}



	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
