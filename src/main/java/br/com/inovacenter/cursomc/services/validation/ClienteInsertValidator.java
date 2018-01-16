package br.com.inovacenter.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.inovacenter.cursomc.dto.ClienteNewDTO;
import br.com.inovacenter.cursomc.entity.Cliente;
import br.com.inovacenter.cursomc.entity.enums.TipoCliente;
import br.com.inovacenter.cursomc.repositories.ClienteRepository;
import br.com.inovacenter.cursomc.resources.exceptions.FieldMessage;
import br.com.inovacenter.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getId())
				&& !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}
		
		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getId())
				&& !BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
