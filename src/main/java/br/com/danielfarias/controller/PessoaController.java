package br.com.danielfarias.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(method = RequestMethod.GET)
	//@CrossOrigin(origins = "http://localhost:4200")
	public List<Pessoa> list(@Param("nome") String nome, @Param("cpf") String cpf) {
		return getPessoa(nome,cpf);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> create(@RequestBody Pessoa pessoa) {
		atribuiTelefonePessoa(pessoa);
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	

	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> update(@RequestBody Pessoa pessoa) {
		atribuiTelefonePessoa(pessoa);
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//@CrossOrigin(origins = "http://localhost:4200")
	public Optional<Pessoa> get(@PathVariable("id") Long id) {
		return pessoaRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		pessoaRepository.deleteById(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	private void atribuiTelefonePessoa(Pessoa pessoa) {
		for(Telefone telefone: pessoa.getTelefones()) {
			if(telefone.getPessoa() == null){
				telefone.setPessoa(pessoa);
			}
		}
	}
	
	private List<Pessoa> getPessoa(String nome,String cpf){
		if(!nome.equals("") && cpf.equals("")){
			return pessoaRepository.findByNomeIgnoreCaseContaining(nome);
		}else if(nome.equals("") && !cpf.equals("")){
			return pessoaRepository.findByCpf(cpf);
		}else if(!nome.equals("") && !cpf.equals("")){
			return pessoaRepository.findByCpfAndNomeIgnoreCaseContaining(cpf, nome);
		}else{
			return pessoaRepository.findAll();
		}
	}

}
