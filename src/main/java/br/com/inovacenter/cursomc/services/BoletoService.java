package br.com.inovacenter.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.inovacenter.cursomc.entity.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instanteDoPedido) {
		Calendar data = Calendar.getInstance();
		data.setTime(instanteDoPedido);
		data.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(data.getTime());
	}
	
}
