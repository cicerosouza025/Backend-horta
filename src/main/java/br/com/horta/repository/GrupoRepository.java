package br.com.horta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.horta.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
