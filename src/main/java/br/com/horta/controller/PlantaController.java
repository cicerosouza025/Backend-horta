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

import br.com.horta.controller.openapi.PlantaControllerOpenAPI;
import br.com.horta.model.Planta;
import br.com.horta.security.permissoes.CheckSecurity;
import br.com.horta.service.PlantaService;

@CrossOrigin
@RestController
@RequestMapping("/planta")
public class PlantaController implements PlantaControllerOpenAPI{
	
	@Autowired
	private PlantaService service;
	
	@CheckSecurity.Planta.Administrador
	@Override
	@PostMapping
	public void salvar(@RequestBody Planta planta) {
		service.salvar(planta);
	}
	
	@Override
	@GetMapping
	public List<Planta> listar(){
		return service.listar();
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Planta> buscar(@PathVariable Long id) {
		
		Optional<Planta> cliente = service.buscar(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CheckSecurity.Planta.Administrador
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Planta planta, @PathVariable Long id){
		
		Planta plantaAtual = service.buscar(id).orElse(null);
		
		if(plantaAtual != null) {
			BeanUtils.copyProperties(planta, plantaAtual, "id");
			service.atualizar(plantaAtual);
			return ResponseEntity.ok(plantaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CheckSecurity.Planta.Administrador
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Planta> excluir(@PathVariable Long id){
		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
