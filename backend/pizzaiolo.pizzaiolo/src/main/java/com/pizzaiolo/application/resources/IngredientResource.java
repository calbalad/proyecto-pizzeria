package com.pizzaiolo.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pizzaiolo.application.dtos.IngredientEditDTO;
import com.pizzaiolo.application.dtos.IngredientShortDTO;
import com.pizzaiolo.domains.contracts.services.IngredientService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/ingredientes")
@Api(value = "/pizzas", description = "Mantenimiento de ingredientes", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class IngredientResource {

	@Autowired
	private IngredientService srv;

	@GetMapping
	@ApiOperation(value = "Listado de los ingredientes")
	public List<IngredientShortDTO> getAll() {
		return srv.getByProjection(IngredientShortDTO.class);
	}
	
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los ingredientes")
	public Page<IngredientShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, IngredientShortDTO.class);
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Recupera un ingrediente")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Ingrediente encontrado"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public IngredientEditDTO getOneEdit(@ApiParam(value = "Identificador del ingrediente") @PathVariable int id)
			throws NotFoundException {
			return IngredientEditDTO.from(srv.getOne(id));
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo ingrediente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Ingrediente añadido"),
		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public ResponseEntity<Object> create(@Valid @RequestBody IngredientEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = IngredientEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		entity = srv.getOne(entity.getIdIngredient());
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdIngredient()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un ingrediente existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Ingrediente desactivado"),
		@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public void update(@PathVariable int id, @Valid @RequestBody IngredientEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getIdIngredient())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

}