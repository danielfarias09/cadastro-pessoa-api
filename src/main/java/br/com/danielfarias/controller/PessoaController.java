package br.com.danielfarias.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Pessoa> list() {
		List<Pessoa> pessoas = this.pessoaRepository.findAll();
		return pessoas;
	}
	/*@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Pessoa pessoa) {
		return new ModelAndView("pessoas/view","pessoa", pessoa);
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> create(@RequestBody Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	/*@GetMapping(value = "editar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Pessoa pessoa) {
		return new ModelAndView(PESSOA_URI + "form","pessoa",pessoa);
	}
	
	@GetMapping(value = "remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {
		this.pessoaRepository.deleteById(id);
		List<Pessoa> pessoas = this.pessoaRepository.findAll();
		
		ModelAndView mv = new ModelAndView(PESSOA_URI + "list","pessoas",pessoas);
		mv.addObject("globalMessage","Pessoa removida com sucesso");
	
		return mv;
	}*/

}
