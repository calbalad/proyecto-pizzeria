package com.pizzaiolo.application.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pizzaiolo.application.dtos.CommentShortDTO;
import com.pizzaiolo.application.dtos.PizzaDetailsDTO;
import com.pizzaiolo.application.dtos.PizzaEditDTO;
import com.pizzaiolo.application.dtos.PizzaShortDTO;
import com.pizzaiolo.domains.contracts.services.PizzaService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/pizzas")
//Todo: cambiar documentacion
@Api(value = "/pizzas", description = "Mantenimiento de pizzas", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class PizzaResource {

	@Autowired
	private PizzaService srv;

	@GetMapping
	@ApiOperation(value = "Listado de las pizzas")
	public List<PizzaShortDTO> getAll() {
		return srv.getByProjection(PizzaShortDTO.class);
	}
	
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de las pizzas")
	public Page<PizzaShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, PizzaShortDTO.class);
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Recupera una pizza")
	public PizzaDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return PizzaDetailsDTO.from(srv.getOne(id));
	}
	
	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera una pizza")
//	todo implementar swagger
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Alquiler encontrado"),
//		@ApiResponse(code = 404, message = "Alquiler no encontrado")
//	})
	public PizzaEditDTO getOneEdit(@ApiParam(value = "Identificador de la pizza") @PathVariable int id, 
			@ApiParam(value = "Versión completa o editable", required = true, allowableValues = "details,edit") String mode)
			throws NotFoundException {
			return PizzaEditDTO.from(srv.getOne(id));
	}
	
	@GetMapping(path = "/{id}/comentarios")
	@Transactional
	public List<CommentShortDTO> getComments(@PathVariable int id) throws NotFoundException {
		return srv.getOne(id).getComments().stream().map(item -> CommentShortDTO.from(item)).collect(Collectors.toList());
	}
	
	/*
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nueva pizza")
	
//	todo implementar swagger
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Pizza añadida"),
//		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
//		@ApiResponse(code = 404, message = "Pizza no encontrada")
//	})
	public ResponseEntity<Object> create(@Valid @RequestBody PizzaEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PizzaEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
//		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdPizza()).toUri();
		return ResponseEntity.created(location).build();

	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar una pizza existente")
//	todo implementar swagger y cambiar documentación
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Pizza borrada"),
//		@ApiResponse(code = 404, message = "Pizza no encontrada")
//	})
	public void delete(@ApiParam(value = "Identificador de la pizza") @PathVariable int id) {
		srv.deleteById(id);
	}*/
	
}
