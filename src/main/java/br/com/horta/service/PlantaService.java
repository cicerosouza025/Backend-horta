package br.com.horta.service;

import java.util.ArrayList;
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
import br.com.horta.model.Praga;
import br.com.horta.repository.PlantaRepository;
import br.com.horta.repository.PragaRepository;
import br.com.horta.request.PlantaRequest;

@Service
public class PlantaService {
	
	@Autowired
	private PlantaRepository plantaRepository;
	
	@Autowired
	private PragaRepository pragaRepository;
	
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

	@Transactional
	public void incluirPraga(Long plantaId, Long pragaId) {
		Optional<Planta> planta = plantaRepository.findById(plantaId);

		planta.ifPresent(planta1 -> {
			Optional<Praga> praga = pragaRepository.findById(pragaId);
			praga.ifPresent(praga1 -> {
				List<Praga> pragas = planta1.getPragas();
				pragas.add(praga1);
				planta1.setPragas(pragas);
			});
			plantaRepository.save(planta1);
		});
	}
	
	@Transactional
	public void deletePragaPlanta(Long plantaId, Long pragaId){
		
		Optional<Planta> planta = plantaRepository.findById(plantaId);

		planta.ifPresent(planta1 -> {
			Optional<Praga> praga = pragaRepository.findById(pragaId);
			praga.ifPresent(praga1 -> {
				List<Praga> pragas = planta1.getPragas();
				pragas.remove(praga1);
			});
			plantaRepository.save(planta1);
		});
	}
	
	public List<Praga> listarPragasDaPlanta(Long plantaId){

		List<Praga> pragas = new ArrayList<Praga>();
		Optional<Planta> planta = plantaRepository.findById(plantaId);
		
		planta.ifPresent(planta1 -> {
			pragas.addAll(planta1.getPragas());
		});
		return pragas;

	}

}
