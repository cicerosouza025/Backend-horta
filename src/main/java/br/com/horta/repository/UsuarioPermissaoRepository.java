package br.com.horta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.horta.model.UsuarioPermissao;

@Repository
public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Long> {

	Optional<UsuarioPermissao> findByEmail(String email);
	
}
