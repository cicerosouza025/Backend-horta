package br.com.horta.request;

import br.com.horta.model.Imagem;
import br.com.horta.model.TipoPraga;
import lombok.Data;

@Data
public class PragaRequest {
	
	private Long id;
	private String nomePopular;
	private String nomeCientifico;
	private String descricao;
	private String tratamento;
	private TipoPraga tipo;
	private String dano;
	private String controle;
	private Imagem foto;

}
