package br.com.danielfarias.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.danielfarias.util.IdadeConverter;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String cpf;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	private String email;
	
	@Transient
	private String idade;
	
	@OneToMany(mappedBy	="pessoa",fetch=FetchType.EAGER, orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private List<Telefone> telefones;
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getIdade() {
		if(dataNascimento != null) {
			return IdadeConverter.calcularIdade(dataNascimento);
		}
		return null;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
}