package br.com.danielfarias.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.danielfarias.dao.PessoaDao;
import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.to.PessoaTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaDaontegrationTest {
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@Test
	public void listaPessoas() {	
		List<Pessoa> pessoas = pessoaDao.findPessoas(new PessoaTO("",""));
		assertThat(pessoas.size()).isGreaterThan(1);		
	}
	
	@Test
	public void buscaPessoa() {
		List<Pessoa> pessoas = pessoaDao.findPessoas(new PessoaTO("Daniel",""));
		assertThat(pessoas).isNotEmpty();
		assertThat(pessoas.get(0).getNome()).isEqualTo("Daniel Farias");
	}

}
