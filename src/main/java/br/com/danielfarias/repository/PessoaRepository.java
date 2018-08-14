package br.com.danielfarias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danielfarias.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findByCpf(String cpf);
	
	List<Pessoa> findByNomeIgnoreCaseContaining(String nome);
	
	List<Pessoa> findByCpfAndNomeIgnoreCaseContaining(String cpf, String nome);

}
