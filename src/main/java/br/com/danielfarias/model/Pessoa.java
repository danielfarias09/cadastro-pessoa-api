package br.com.danielfarias.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pessoa implements BaseEntity<Long>{
	
	private static final long serialVersionUID = -4032798442163494733L;

	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	private String cpf;

	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataNascimento;
	
	@NotNull
	private String email;
	
	@Transient
	private String idade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="pessoa_id", nullable = false)
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
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
			Period idade = Period.between(dataNascimento, LocalDate.now());
			return idade.getYears() + " anos";
		}
		return null;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
}