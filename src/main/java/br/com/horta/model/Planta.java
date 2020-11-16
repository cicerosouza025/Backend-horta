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
@Table(name = "planta")
public class Planta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TipoIluminacao iluminacao;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TipoUmidade umidade;
	
	@Column
	private String descricao;
	
	@Column
	private String nomeci;
	
	@Column
	private String nomepo;
	
	@OneToOne
	private Imagem foto;

}
