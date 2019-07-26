package br.com.danielfarias.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danielfarias.model.Usuario;
import br.com.danielfarias.model.UsuarioDTO;
import br.com.danielfarias.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario  = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username);
		}	
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
				new ArrayList<>());
	}
	
	public Usuario save(UsuarioDTO usuario) {
		Usuario novoUsuario = new Usuario();
		novoUsuario.setUsername(usuario.getUsername());
		novoUsuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(novoUsuario);	
	}

}
