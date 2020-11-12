package br.com.horta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.horta.controller.openapi.PragaControllerOpenAPI;
import br.com.horta.model.Praga;
import br.com.horta.security.permissoes.CheckSecurity;
import br.com.horta.service.PragaService;

@CrossOrigin
@RestController
@RequestMapping("/praga")
public class PragaController implements PragaControllerOpenAPI {

	@Autowired
	private PragaService pragaService;
	
	//@CheckSecurity.Praga.Administrador
	@Override
	@PostMapping
	public void salvar(@RequestBody Praga praga) {
		
		pragaService.salvar(praga);
	}
	
	@Override
	@GetMapping
	public List<Praga> listar(){
		
		return pragaService.listar();
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Praga> buscarPorId(@PathVariable Long id){
		
		Optional<Praga> praga = pragaService.buscarPorId(id);
		
		if(praga.isPresent()) {
			return ResponseEntity.ok(praga.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//@CheckSecurity.Praga.Administrador
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Praga praga, @PathVariable Long id){
		
		Praga pragaAtual = pragaService.buscarPorId(id).orElse(null);
		
		if(pragaAtual != null) {
			BeanUtils.copyProperties(praga, pragaAtual, "id");
			pragaService.atualizar(pragaAtual);
			return ResponseEntity.ok(pragaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//@CheckSecurity.Praga.Administrador
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Praga> excluir(@PathVariable Long id){
		
		try {
			pragaService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
