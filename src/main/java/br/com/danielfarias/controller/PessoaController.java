package br.com.danielfarias.controller;

import java.util.List;

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
import br.com.danielfarias.service.PessoaService;
import br.com.danielfarias.to.PessoaTO;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pessoa> list(@Param("nome") String nome, @Param("cpf") String cpf) {
		return pessoaService.findPessoas(new PessoaTO(nome,cpf));
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody Pessoa pessoa) {
		pessoaService.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<HttpStatus> update(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) {
		pessoaService.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Pessoa get(@PathVariable("id") Long id) {
		return pessoaService.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		pessoaService.delete(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
}
