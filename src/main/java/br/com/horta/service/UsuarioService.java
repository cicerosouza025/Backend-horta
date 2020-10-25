package br.com.horta.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.horta.dto.UsuarioDTO;
import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.mapper.UsuarioMapper;
import br.com.horta.model.Usuario;
import br.com.horta.repository.PlantaRepository;
import br.com.horta.repository.UsuarioRepository;
import br.com.horta.request.UsuarioRequest;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PlantaRepository plantaRepository;
	
	@Autowired
	private UsuarioMapper mapper;

	@Transactional
	public UsuarioDTO salvar(UsuarioRequest usuarioRequest) {
	
		Usuario usuario = mapper.requesTotModel(usuarioRequest) ;
		
		//usuario.getPlantas().stream().forEach(planta -> planta.setUsuario(usuario));
	
		return mapper.modelToDTO(repository.save(usuario));
	}

	public List<UsuarioDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(usu -> mapper.modelToDTO(usu))
				.collect(Collectors.toList());
	}

	public Optional<Usuario> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradodException(id);
		};
		
	}

	@Transactional
	public void atualizar(Usuario usuario) {
		
		repository.save(usuario);
	}

}
