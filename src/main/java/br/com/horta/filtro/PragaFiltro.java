package br.com.horta.filtro;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Informações básicas sobre a praga", value = "Praga")
public class PragaFiltro {
	
	 @ApiModelProperty(value = "O tipo de praga a ser buscada", required = false, position = 1, dataType = "String", example = "Formiga")
	    private List<String> nome;

}
