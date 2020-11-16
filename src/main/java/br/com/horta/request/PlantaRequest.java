package br.com.horta.request;


import br.com.horta.model.Imagem;
import br.com.horta.model.TipoIluminacao;
import br.com.horta.model.TipoUmidade;
import lombok.Data;

@Data
public class PlantaRequest {
	
	private Long id;
	private TipoIluminacao iluminacao;
	private TipoUmidade umidade;
	private String descricao;
	private String nomeci;
	private String nomepo;
	private Imagem foto;

}
