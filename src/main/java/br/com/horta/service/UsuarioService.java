package br.com.horta.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.horta.dto.UsuarioDTO;
import br.com.horta.email.EnvioEmailService;
import br.com.horta.email.Mensagem;
import br.com.horta.exception.ClienteNaoEncontradodException;
import br.com.horta.mapper.UsuarioMapper;
import br.com.horta.model.Grupo;
import br.com.horta.model.Planta;
import br.com.horta.model.Usuario;
import br.com.horta.repository.GrupoRepository;
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
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private EnvioEmailService envioEmail;

	@Transactional
	public UsuarioDTO salvar(UsuarioRequest usuarioRequest) {
	
		Grupo grupo = grupoRepository.findById(1L).get();	
		
		Usuario usuario = mapper.requesTotModel(usuarioRequest) ;
		
		usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(usuario.getNome() + " - Cadastro feito com sucesso")
				.corpo("usuario-cadastrado.html")
				.variavel("usuario", usuario)
				.destinatario(usuario.getEmail())
				.build();
		
		//usuario.getPlantas().stream().forEach(planta -> planta.setUsuario(usuario));
		
		Set<Grupo> grupos = new HashSet<>();
		grupos.add(grupo);
		usuario.setGrupos(grupos);
		
		envioEmail.enviar(mensagem);
	
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
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(usuario.getNome() + " - Usuário atualizado!")
				.corpo("usuario-atualizado.html")
				.variavel("usuario", usuario)
				.destinatario(usuario.getEmail())
				.build();
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		envioEmail.enviar(mensagem);
		
		repository.save(usuario);
	}
	
	@Transactional
	public void incluirPlanta(Long id, Long plantaId) {
		Optional<Usuario> usuario = repository.findById(id);

		usuario.ifPresent(usuario1 -> {
			Optional<Planta> planta = plantaRepository.findById(plantaId);
			planta.ifPresent(planta1 -> {
				List<Planta> plantas = usuario1.getPlantas();
				plantas.add(planta1);
				usuario1.setPlantas(plantas);
			});
			repository.save(usuario1);
		});
	}

	@Transactional
	public void deletePlantaUsuario(Long usuarioId, Long plantaId) {
		
		Optional<Usuario> usuario = repository.findById(usuarioId);

		usuario.ifPresent(usuario1 -> {
			Optional<Planta> planta = plantaRepository.findById(plantaId);
			planta.ifPresent(planta1 -> {
				List<Planta> plantas = usuario1.getPlantas();
				plantas.remove(planta1);
			});
			repository.save(usuario1);
		});
		
	}
}


