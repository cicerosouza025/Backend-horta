package br.com.horta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.horta.controller.openapi.UsuarioControllerOpenAPI;
import br.com.horta.dto.UsuarioDTO;
import br.com.horta.model.Usuario;
import br.com.horta.request.UsuarioRequest;
import br.com.horta.security.permissoes.CheckSecurity;
import br.com.horta.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioControllerOpenAPI {
	
	@Autowired
	private UsuarioService service;
	
	
	@Override
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuarioRequest){
		try {
			UsuarioDTO usuario = service.salvar(usuarioRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
			
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@CheckSecurity.Usuario.Administrador
	@Override
	@GetMapping
	public List<UsuarioDTO> listar(){
		return service.listar();
	}
	
	@CheckSecurity.Usuario.Administrador
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id){
		
		Optional<Usuario> usuario = service.buscar(id);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@CheckSecurity.Usuario.Logado
	@CheckSecurity.Usuario.Administrador
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity <Usuario> excluir(@PathVariable Long id){
		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//@CheckSecurity.Usuario.Logado
	//@CheckSecurity.Usuario.Administrador
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario, @PathVariable Long id) {
		
		Usuario usuarioAtual = service.buscar(id).orElse(null);
		
		if (usuarioAtual != null) {
			BeanUtils.copyProperties(usuario, usuarioAtual, "id");
			
			service.atualizar(usuarioAtual);
			return ResponseEntity.ok(usuarioAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	

	@PostMapping("/{usuarioId}/plantas/{plantaId}")
	public ResponseEntity<?> incluirPlanta(@PathVariable Long usuarioId, @PathVariable Long plantaId){
		try {
			service.incluirPlanta(usuarioId, plantaId);
			return  ResponseEntity.ok().body("adicionado com sucesso");

		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{usuarioId}/plantas/{plantaId}")
	public ResponseEntity<?> deletePlantaUsuario(@PathVariable Long usuarioId, @PathVariable Long plantaId){
		try {
			service.deletePlantaUsuario(usuarioId, plantaId);
			return  ResponseEntity.ok().body("excluido com sucesso");

		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
}
