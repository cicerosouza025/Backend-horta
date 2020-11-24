package br.com.horta.dto;

import java.util.List;

import br.com.horta.model.Praga;
import lombok.Data;

@Data
public class PlantaDTO {
	
	private Long id;
	private String luz;
	private String solo;
	private String descricao;
	private String nomeci;
	private String nomepo;
	private String temperatura;
	private String epoca;
	private String tempo;
	private String irrigacao;
	private List<Praga> pragas;
	private ImagemDTO foto;

}
