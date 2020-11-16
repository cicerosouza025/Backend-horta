package br.com.horta.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.horta.dto.PragaDTO;
import br.com.horta.model.Praga;
import br.com.horta.request.PragaRequest;

@Component
public class PragaMapper {

	@Autowired
    private ModelMapper modelMapper;
	
	public Praga requestToModel(PragaRequest pragaRequest) {
		return modelMapper.map(pragaRequest, Praga.class);
	}
	
	public PragaDTO modelToDTO(Praga praga) {
        return modelMapper.map(praga, PragaDTO.class);
    }

}
