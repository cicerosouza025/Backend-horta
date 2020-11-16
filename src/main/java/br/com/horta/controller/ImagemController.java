package br.com.horta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.horta.controller.openapi.ImagemControllerOpenAPI;
import br.com.horta.dto.ImagemDTO;
import br.com.horta.request.ImagemRequest;
import br.com.horta.service.ImagemService;

@CrossOrigin
@RestController
@RequestMapping("/imagem")
public class ImagemController implements ImagemControllerOpenAPI {
	
	@Autowired
	private ImagemService service;
	
	//@CheckSecurity.Imagem.SalvarImagem
	@Override
	@PostMapping
	public ImagemDTO salvarFoto(ImagemRequest imagem) {
		
		return service.salvar(imagem);
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ImagemDTO> excluir(@PathVariable Long id) {
		try {
			service.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
