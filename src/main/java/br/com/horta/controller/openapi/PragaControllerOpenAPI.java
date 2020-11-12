package br.com.horta.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.horta.exception.config.Problem;
import br.com.horta.model.Praga;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Praga")
public interface PragaControllerOpenAPI {
	
	@ApiOperation("Cadastrar uma praga")
	@ApiResponses({ @ApiResponse(code = 201, message = "Praga cadastrada", response = Praga.class) })
	void salvar(
		@ApiParam(name = "corpo", value = "Representação de uma nova praga", required = true) 
		@Valid Praga praga);
	
	@ApiOperation(value = "Buscar todas as Pragas", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todas as Pragas", response = Praga.class) })
	List<Praga> listar();
	
	@ApiOperation(value = "Buscar Praga pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Praga pelo ID", response = Praga.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Praga> buscarPorId(Long id);
	
	@ApiOperation(value = "Atualizar Praga pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Praga atualizada com sucesso.", response = Praga.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma nova praga", required = true) @Valid Praga planta,
			Long id);
	
	@ApiOperation(value = "Excluir Praga pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Praga excluída com sucesso", response = Praga.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Praga> excluir(Long id);

}
