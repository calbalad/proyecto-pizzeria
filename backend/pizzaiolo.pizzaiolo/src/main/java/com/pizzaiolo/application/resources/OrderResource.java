package com.pizzaiolo.application.resources;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pizzaiolo.application.dtos.CarritoEditDTO;
import com.pizzaiolo.application.dtos.CestaEditDTO;
import com.pizzaiolo.application.dtos.OrderDetailsDTO;
import com.pizzaiolo.application.dtos.OrderEditDTO;
import com.pizzaiolo.application.dtos.OrderShortDTO;
import com.pizzaiolo.application.dtos.OrderStatusEditDTO;
import com.pizzaiolo.domains.contracts.services.OrderService;
import com.pizzaiolo.domains.contracts.services.PizzaService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/pedidos")
public class OrderResource {

	@Autowired
	private OrderService srv;

	@Autowired
	private PizzaService srvPizza;
	
//	@Autowired
//	private PizzaorderService srvPizzaorder;

	@GetMapping
	@ApiOperation(value = "Listado de los pedidos")
	public List<OrderShortDTO> getAll() {
		return srv.getByProjection(OrderShortDTO.class);
	}

	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los pedidos")
	public Page<OrderShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, OrderShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Detalles de pedido")
	public OrderDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = true, defaultValue = "details") String mode) throws NotFoundException {
		return OrderDetailsDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nuevo pedido")
	public ResponseEntity<Object> create(@Valid @RequestBody OrderEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = OrderEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		BigDecimal flag = BigDecimal.ZERO;
		for (CestaEditDTO value : item.getPizzas()) {
			System.out.println(srvPizza.getOne(value.getIdPizza()));
			flag = flag.add(srvPizza.getOne(value.getIdPizza()).getAmount().multiply(BigDecimal.valueOf(value.getQuantity())));
		}
		entity.setAmount(flag);
		entity = srv.add(entity);
		item.updateAmount(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdOrder()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Actualizar estado de pedido")
	public void update(@PathVariable int id, @Valid @RequestBody OrderStatusEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getIdOrder())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.updateStatus(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar un pedido existente (Pasa a estado cancelado)")
	public void cancelOrder(@PathVariable int id, @Valid @RequestBody OrderStatusEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getIdOrder())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.cancelOrder(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
}
