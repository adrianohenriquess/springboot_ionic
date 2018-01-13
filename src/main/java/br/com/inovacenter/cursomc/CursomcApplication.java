package br.com.inovacenter.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.inovacenter.cursomc.entity.Categoria;
import br.com.inovacenter.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Informática");
		Categoria categoria2 = new Categoria();
		categoria2.setNome("Escritório");
		
		categoriaRepository.save(Arrays.asList(categoria1, categoria2));
	}
}
