package br.com.horta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.horta.dto.PragaDTO;
import br.com.horta.model.Praga;

@Repository
public interface PragaRepository extends JpaRepository<Praga, Long> {
	
	@Query("from Praga where nome like :nome")
	List<Praga> findAll(List<String> nome);

	

}
