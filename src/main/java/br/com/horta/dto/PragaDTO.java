package br.com.horta.dto;

import br.com.horta.model.TipoPraga;
import lombok.Data;

@Data
public class PragaDTO {
	
	private Long id;
	private String nomePopular;
	private String nomeCientifico;
	private String descricao;
	private String tratamento;
	private TipoPraga tipo;
	private String dano;
	private String controle;
	private ImagemDTO foto;

}
