package br.com.horta.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.horta.dto.PlantaDTO;
import br.com.horta.exception.config.Problem;
import br.com.horta.model.Planta;
import br.com.horta.request.PlantaRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller da Planta")
public interface PlantaControllerOpenAPI {


	
	@ApiOperation("Cadastrar uma planta")
	@ApiResponses({ @ApiResponse(code = 201, message = "Planta cadastrada", response = PlantaDTO.class) })
	ResponseEntity<?> salvar(
		@ApiParam(name = "corpo", value = "Representação de uma nova planta", required = true) 
		@Valid PlantaRequest plantaRequest);
	
	@ApiOperation(value = "Buscar todas as Plantas", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todas as Plantas", response = PlantaDTO.class) })
	List<PlantaDTO> listar();
	
	@ApiOperation(value = "Buscar Planta pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Planta pelo ID", response = Planta.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Planta> buscar(Long id);
	
	@ApiOperation(value = "Atualizar Planta pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Planta atualizada com sucesso.", response = Planta.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma nova planta", required = true) @Valid Planta planta,
			Long id);
	
	@ApiOperation(value = "Excluir Planta pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Planta excluída com sucesso", response = Planta.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Planta> excluir(Long id);
}