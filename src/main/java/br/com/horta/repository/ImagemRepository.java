package br.com.horta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.horta.model.Imagem;

@Repository
public interface ImagemRepository  extends JpaRepository<Imagem, Long>{

}
