package br.com.horta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.horta.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
