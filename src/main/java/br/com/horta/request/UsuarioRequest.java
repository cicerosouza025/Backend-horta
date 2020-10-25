package br.com.horta.request;

import java.util.List;

import br.com.horta.model.Planta;
import br.com.horta.model.TipoGenero;
import lombok.Data;

@Data
public class UsuarioRequest {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private TipoGenero genero;
	private String email;
	private String senha;
	private List<Planta> plantas;

}
