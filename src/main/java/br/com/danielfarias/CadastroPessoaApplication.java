package br.com.danielfarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class CadastroPessoaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CadastroPessoaApplication.class, args);
	}
}
