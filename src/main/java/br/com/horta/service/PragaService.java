package br.com.horta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.horta.dto.PragaDTO;
import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.mapper.PragaMapper;
import br.com.horta.model.Praga;
import br.com.horta.repository.PragaRepository;
import br.com.horta.request.PragaRequest;

@Service
public class PragaService {
	
	@Autowired
	private PragaRepository pragaRepository;
	
	@Autowired
	private PragaMapper mapper;
	
	@Transactional
	public PragaDTO salvar(PragaRequest pragaRequest) {
		
		Praga praga = mapper.requestToModel(pragaRequest);
		return mapper.modelToDTO( pragaRepository.save(praga) );
	}

	public List<PragaDTO> listar() {
		return pragaRepository.findAll()
				.stream()
				.map(pra -> mapper.modelToDTO(pra))
				.collect(Collectors.toList());
	}

	public Optional<Praga> buscarPorId(Long id) {
		return pragaRepository.findById(id);
	}

	@Transactional
	public void atualizar(Praga pragaAtual) {
		pragaRepository.save(pragaAtual);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			pragaRepository.deleteById(id);
			pragaRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradodException(id);
		}
	}

}
