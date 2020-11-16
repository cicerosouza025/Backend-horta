package br.com.horta.dto;


import br.com.horta.model.TipoIluminacao;
import br.com.horta.model.TipoUmidade;
import lombok.Data;

@Data
public class PlantaDTO {
	
	private Long id;
	private TipoIluminacao iluminacao;
	private TipoUmidade umidade;
	private String descricao;
	private String nomeci;
	private String nomepo;
	private ImagemDTO foto;

}
