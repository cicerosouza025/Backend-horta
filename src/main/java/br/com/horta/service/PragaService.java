package br.com.horta.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.model.Praga;
import br.com.horta.repository.PragaRepository;

@Service
public class PragaService {
	
	@Autowired
	private PragaRepository pragaRepository;
	
	@Transactional
	public void salvar(@Valid Praga praga) {
		pragaRepository.save(praga);	
	}

	public List<Praga> listar() {
		return pragaRepository.findAll();
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
