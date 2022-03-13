package com.pizzaiolo.application.resources;

import java.net.URI;
import java.util.List;

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

import com.pizzaiolo.application.dtos.CommentDetailsDTO;
import com.pizzaiolo.application.dtos.CommentEditDTO;
import com.pizzaiolo.application.dtos.CommentShortDTO;
import com.pizzaiolo.domains.contracts.services.CommentService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/comentarios")
//Todo: cambiar documentacion
@Api(value = "/comentarios", description = "Mantenimiento de películas", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class CommentResource {

	@Autowired
	private CommentService srv;

	@GetMapping
	@ApiOperation(value = "Listado de las películas")
	public List<CommentShortDTO> getAll() {
		return srv.getByProjection(CommentShortDTO.class);
	}
	
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los comentarios")
	public Page<CommentShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, CommentShortDTO.class);
	}
	
	@GetMapping(path = "/{id}")
	public CommentDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return CommentDetailsDTO.from(srv.getOne(id));
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nueva película")
	
//	todo implementar swagger
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Película añadida"),
//		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
//		@ApiResponse(code = 404, message = "Película no encontrada")
//	})
	public ResponseEntity<Object> create(@Valid @RequestBody CommentEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = CommentEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
//		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdComment()).toUri();
		return ResponseEntity.created(location).build();

	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar una película existente")
//	todo implementar swagger y cambiar documentación
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Película borrada"),
//		@ApiResponse(code = 404, message = "Película no encontrada")
//	})
	public void delete(@ApiParam(value = "Identificador de la película") @PathVariable int id) {
		srv.deleteById(id);
	}
	
}
