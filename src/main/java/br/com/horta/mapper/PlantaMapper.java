package br.com.horta.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.horta.dto.PlantaDTO;
import br.com.horta.model.Planta;
import br.com.horta.request.PlantaRequest;

@Component
public class PlantaMapper {
	
	@Autowired
    private ModelMapper modelMapper;
	
	  public Planta requestToModel(PlantaRequest plantaRequest) {
	        return modelMapper.map(plantaRequest, Planta.class);
	    }
	    
	    public PlantaDTO modelToDTO(Planta planta) {
	        return modelMapper.map(planta, PlantaDTO.class);
	    }

}
