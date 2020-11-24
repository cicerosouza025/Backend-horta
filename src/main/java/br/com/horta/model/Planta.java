package br.com.horta.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String luz;
	
	@Column
	private String solo;
	
	@Column
	private String descricao;
	
	@Column
	private String nomeci;
	
	@Column
	private String nomepo;
	
	@Column
	private String temperatura;
	
	@Column
	private String epoca;
	
	@Column
	private String tempo;
	
	@Column
	private String irrigacao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "praga_planta", joinColumns = @JoinColumn(name = "plant_id"),
			inverseJoinColumns = @JoinColumn(name = "praga_id"))
	private List<Praga> pragas;
	
	@OneToOne
	private Imagem foto;

}
