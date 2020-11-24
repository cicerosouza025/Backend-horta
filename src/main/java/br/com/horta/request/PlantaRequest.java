package br.com.horta.request;


import java.util.List;

import br.com.horta.model.Imagem;
import br.com.horta.model.Praga;
import lombok.Data;

@Data
public class PlantaRequest {
	
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
	private Imagem foto;

}
