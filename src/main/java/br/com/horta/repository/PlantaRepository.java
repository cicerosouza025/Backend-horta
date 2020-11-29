package br.com.horta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.horta.model.Planta;


@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {
	


}
