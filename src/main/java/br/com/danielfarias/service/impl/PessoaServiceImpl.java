package br.com.danielfarias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielfarias.dao.BaseDao;
import br.com.danielfarias.dao.PessoaDao;
import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.model.Telefone;
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
		atribuiTelefonePessoa(pessoa);
		return pessoaDao.save(pessoa);
	}
	
	private void atribuiTelefonePessoa(Pessoa pessoa) {	
		if(pessoa.getTelefones() != null) {
			for(Telefone telefone: pessoa.getTelefones()) {
				if(telefone.getPessoa() == null){
					telefone.setPessoa(pessoa);
				}
			}
		}
	}

	@Override
	public List<Pessoa> findPessoas(PessoaTO to) {
		return pessoaDao.findPessoas(to);
	}

}
