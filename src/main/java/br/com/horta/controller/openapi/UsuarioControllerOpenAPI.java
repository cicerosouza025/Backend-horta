package br.com.horta.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.horta.dto.UsuarioDTO;
import br.com.horta.exception.config.Problem;
import br.com.horta.model.Usuario;
import br.com.horta.request.UsuarioRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Usuario")
public interface UsuarioControllerOpenAPI {
	
	@ApiOperation("Cadastrar um Usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "Usuario cadastrado", response = UsuarioDTO.class) })	
	ResponseEntity<?> salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo usuario", required = true)
			@Valid UsuarioRequest usuarioRequest);
	
	@ApiOperation(value = "Buscar todos os Usuarios", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar todos os usuarios", response = UsuarioDTO.class) })
	List<UsuarioDTO> listar();

	@ApiOperation(value = "Buscar Usuario pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Usuario pelo ID", response = UsuarioDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Usuario> buscar(Long id);
	
	@ApiOperation(value = "Excluir Usuario pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Usuario excluído com sucesso", response = UsuarioDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Usuario> excluir(Long id);
	
	@ApiOperation(value = "Atualizar Usuario pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuario atualizado com sucesso.", response = UsuarioDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de um novo cliente", required = true) @Valid Usuario usuario,
			Long id);
}
