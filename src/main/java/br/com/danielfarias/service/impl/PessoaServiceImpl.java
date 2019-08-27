package br.com.danielfarias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielfarias.dao.BaseDao;
import br.com.danielfarias.dao.PessoaDao;
import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.service.PessoaService;
import br.com.danielfarias.to.PessoaTO;


@Service("pessoaService")
public class PessoaServiceImpl extends BaseServiceImpl<Pessoa> implements PessoaService{
	
	@Autowired
	private PessoaDao pessoaDao;

	@Override
	public BaseDao<Pessoa> getDao() {
		return pessoaDao;
	}
	
	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaDao.save(pessoa);
	}

	@Override
	public List<Pessoa> findPessoas(PessoaTO to) {
		return pessoaDao.findPessoas(to);
	}

}
