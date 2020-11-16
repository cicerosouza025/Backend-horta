package br.com.horta.dto;

import lombok.Data;

@Data
public class PragaDTO {
	
	private Long id;
	private String nomePopular;
	private String nomeCientifico;
	private String descricao;
	private String tratamento;
	private ImagemDTO foto;

}
