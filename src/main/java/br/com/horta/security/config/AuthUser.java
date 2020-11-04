package br.com.horta.security.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.horta.model.UsuarioPermissao;
import lombok.Getter;

@Getter
public class AuthUser extends User {

private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String nomeCompleto;
	
	public AuthUser(UsuarioPermissao usuarioPermissao, Collection<? extends GrantedAuthority> permissoes) {
		super(usuarioPermissao.getEmail(), usuarioPermissao.getSenha(), permissoes);
		
		this.userId = usuarioPermissao.getId();
		this.nomeCompleto = usuarioPermissao.getNome();
	}
	
}
