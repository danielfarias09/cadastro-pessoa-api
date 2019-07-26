package br.com.danielfarias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.model.Telefone;
import br.com.danielfarias.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public List<Pessoa> getPessoas(String nome, String cpf) {
		nome = (nome == "" ? null : nome);
		cpf = (cpf == "" ? null : cpf);
		if(nome != null && cpf == null){
			return pessoaRepository.findByNomeIgnoreCaseContaining(nome);
		}else if(nome == null && cpf != null){
			return pessoaRepository.findByCpf(cpf);
		}else if(nome != null && cpf != null){
			return pessoaRepository.findByCpfAndNomeIgnoreCaseContaining(cpf, nome);
		}else{
			return pessoaRepository.findAll();
		}
	}

	public List<Pessoa> findByCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

	public List<Pessoa> findByNome(String nome) {
		return pessoaRepository.findByNomeIgnoreCaseContaining(nome);
	}

	public List<Pessoa> findByCpfAndNome(String cpf, String nome) {
		return pessoaRepository.findByCpfAndNomeIgnoreCaseContaining(cpf,nome);
	}
	
	public Pessoa save(Pessoa pessoa) {
		atribuiTelefonePessoa(pessoa);
		return pessoaRepository.save(pessoa);
	}
	
	public void deleteById(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
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
}
