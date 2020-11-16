package br.com.horta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.horta.model.Imagem;

public interface ImagemRepository  extends JpaRepository<Imagem, Long>{

}
