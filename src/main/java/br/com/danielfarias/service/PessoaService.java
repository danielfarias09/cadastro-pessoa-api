package br.com.danielfarias.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.to.PessoaTO;

@NoRepositoryBean
public interface PessoaService extends BaseService<Pessoa>{
	
	List<Pessoa> findPessoas(PessoaTO to);
	
	Pessoa save(Pessoa pessoa);
	
}
