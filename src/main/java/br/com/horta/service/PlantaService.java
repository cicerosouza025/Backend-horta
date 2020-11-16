package br.com.horta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.horta.dto.PlantaDTO;
import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.mapper.PlantaMapper;
import br.com.horta.model.Planta;
import br.com.horta.repository.PlantaRepository;
import br.com.horta.request.PlantaRequest;

@Service
public class PlantaService {
	
	@Autowired
	private PlantaRepository plantaRepository;
	
	@Autowired
	private PlantaMapper mapper;

	@Transactional
	public PlantaDTO salvar(PlantaRequest plantaRequest) {
		
		Planta planta = mapper.requestToModel(plantaRequest);
		return mapper.modelToDTO( plantaRepository.save(planta) );
	}

	public List<PlantaDTO> listar() {	
		return plantaRepository.findAll()
				.stream()
				.map(pla -> mapper.modelToDTO(pla))
				.collect(Collectors.toList());
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
