package br.com.horta.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.model.Planta;
import br.com.horta.repository.PlantaRepository;

@Service
public class PlantaService {
	
	@Autowired
	private PlantaRepository plantaRepository;

	@Transactional
	public void salvar(@Valid Planta planta) {
		plantaRepository.save(planta);	
	}

	public List<Planta> listar() {	
		return plantaRepository.findAll();
	}

	public Optional<Planta> buscar(Long id) {
		return plantaRepository.findById(id);
	}

	@Transactional
	public void atualizar(Planta planta) {
		plantaRepository.save(planta);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			plantaRepository.deleteById(id);
			plantaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradodException(id);
		}	
	}

}
