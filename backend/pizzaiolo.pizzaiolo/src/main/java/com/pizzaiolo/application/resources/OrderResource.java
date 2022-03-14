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
import com.pizzaiolo.application.dtos.OrderDetailsDTO;
import com.pizzaiolo.application.dtos.OrderEditDTO;
import com.pizzaiolo.application.dtos.OrderShortDTO;
import com.pizzaiolo.domains.contracts.services.CommentService;
import com.pizzaiolo.domains.contracts.services.OrderService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/pedidos")
public class OrderResource {

	@Autowired
	private OrderService srv;

	@GetMapping
	@ApiOperation(value = "Listado de los comentarios")
	public List<OrderShortDTO> getAll() {
		return srv.getByProjection(OrderShortDTO.class);
	}
	
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los pedidos")
	public Page<OrderShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, OrderShortDTO.class);
	}
	
	@GetMapping(path = "/{id}")
	public OrderDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return OrderDetailsDTO.from(srv.getOne(id));
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nuevo comentario")
	
//	todo implementar swagger
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Comentario añadido"),
//		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
//		@ApiResponse(code = 404, message = "Comentario no encontrado")
//	})
	public ResponseEntity<Object> create(@Valid @RequestBody OrderEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = OrderEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
//		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdOrder()).toUri();
		return ResponseEntity.created(location).build();

	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar un pedido existente")
//	todo implementar swagger y cambiar documentación
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Comentario borrado"),
//		@ApiResponse(code = 404, message = "Comentario no encontrado")
//	})
	public void delete(@ApiParam(value = "Identificador del pedido") @PathVariable int id) {
		srv.deleteById(id);
	}
	
}
