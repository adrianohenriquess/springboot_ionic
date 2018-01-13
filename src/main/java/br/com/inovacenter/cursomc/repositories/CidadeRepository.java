package br.com.inovacenter.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inovacenter.cursomc.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
