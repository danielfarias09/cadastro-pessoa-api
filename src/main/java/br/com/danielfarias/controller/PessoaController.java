package br.com.danielfarias.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.model.Telefone;
import br.com.danielfarias.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<Pessoa> list(@Param("nome") String nome, @Param("cpf") String cpf) {
		return getPessoa(nome,cpf);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody Pessoa pessoa) {
		atribuiTelefonePessoa(pessoa);
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	public ResponseEntity<HttpStatus> update(@RequestBody Pessoa pessoa) {
		atribuiTelefonePessoa(pessoa);
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Pessoa> get(@PathVariable("id") Long id) {
		return pessoaRepository.findById(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		pessoaRepository.deleteById(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
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
	
	private List<Pessoa> getPessoa(String nome,String cpf){
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

}
