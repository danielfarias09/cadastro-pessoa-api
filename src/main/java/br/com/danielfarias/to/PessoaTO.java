package br.com.danielfarias.to;

public class PessoaTO {
	
	private String nome;
	
	private String cpf;

	public PessoaTO() {
		super();
	}

	public PessoaTO(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
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
}
