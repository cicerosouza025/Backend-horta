package br.com.horta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="praga")
public class Praga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_popular")
	private String nomePopular;
	
	@Column(name="nome_cientifico")
	private String nomeCientifico;
	
	@Column
	private String descricao;
	
	@Column
	private String tratamento;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TipoPraga tipo;
	
	@Column
	private String dano;
	
	@Column
	private String controle;
	
	@OneToOne
	private Imagem foto;

}
