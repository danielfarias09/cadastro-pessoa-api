package br.com.danielfarias.dao;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.to.PessoaTO;

@NoRepositoryBean
public interface PessoaDao extends BaseDao<Pessoa>{
	
	List<Pessoa> findPessoas(PessoaTO pessoa);
}
