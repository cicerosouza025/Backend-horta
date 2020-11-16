package br.com.horta.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.horta.dto.ImagemDTO;
import br.com.horta.model.Imagem;
import br.com.horta.request.ImagemRequest;

@Component
public class ImagemMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Imagem requestToModel(ImagemRequest imagemRequest) {
		return modelMapper.map(imagemRequest, Imagem.class);
	}
	
	public ImagemDTO modelToDTO(Imagem imagem) {
		return modelMapper.map(imagem, ImagemDTO.class);
	}

}
