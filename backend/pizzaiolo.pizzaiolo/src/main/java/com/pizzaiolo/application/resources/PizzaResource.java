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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pizzaiolo.application.dtos.CommentShortDTO;
import com.pizzaiolo.application.dtos.PizzaActiveDetailsDTO;
import com.pizzaiolo.application.dtos.PizzaDetailsDTO;
import com.pizzaiolo.application.dtos.PizzaEditDTO;
import com.pizzaiolo.application.dtos.PizzaShortDTO;
import com.pizzaiolo.application.proxies.LikesProxy;
import com.pizzaiolo.domains.contracts.repositories.PizzaRepository;
import com.pizzaiolo.domains.contracts.services.PizzaService;
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
@RequestMapping("/api/v1/pizzas")
@Api(value = "/pizzas", description = "Mantenimiento de pizzas", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class PizzaResource {

	@Autowired
	private PizzaService srv;
	
	@Autowired
	private PizzaRepository dao;
	
	@Autowired
	private LikesProxy proxy;

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
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pizza encontrada"),
		@ApiResponse(code = 404, message = "Pizza no encontrada")
	})
	public PizzaDetailsDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") @ApiParam(allowableValues = "details,edit,active") String mode)
			throws NotFoundException {
			return PizzaDetailsDTO.from(srv.getOne(id), proxy);
	}
	
	@GetMapping(path = "/{id}", params = "mode=active")
	@ApiOperation(value = "Recupera una pizza")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pizza encontrada"),
		@ApiResponse(code = 404, message = "Pizza no encontrada")
	})
	public PizzaActiveDetailsDTO getOne(@PathVariable int id, @RequestParam(required = false) @ApiParam(allowableValues = "details,edit,active") String mode)
			throws NotFoundException {
			return PizzaActiveDetailsDTO.from(srv.getOne(id));
	}
	
	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera una pizza")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pizza encontrada"),
		@ApiResponse(code = 404, message = "Pizza no encontrada")
	})
	public PizzaEditDTO getOneEdit(@ApiParam(value = "Identificador de la pizza") @PathVariable int id, 
			@ApiParam(value = "Versión completa, editable o activa", required = true, allowableValues = "details,edit,active") String mode)
			throws NotFoundException {
			return PizzaEditDTO.from(srv.getOne(id));
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añade una nueva pizza")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pizza añadida"),
		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
		@ApiResponse(code = 404, message = "Pizza no encontrada")
	})
	public ResponseEntity<Object> create(@Valid @RequestBody PizzaEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PizzaEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		entity = srv.getOne(entity.getIdPizza());
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdPizza()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modifica una pizza existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pizza desactivada"),
		@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
		@ApiResponse(code = 404, message = "Pizza no encontrada")
	})
	public void update(@PathVariable int id, @Valid @RequestBody PizzaEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getIdPizza())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
	
	@GetMapping(path = "/{id}/comentarios")
	@ApiOperation(value = "Recupera los comentarios de una pizza")
	public List<CommentShortDTO> getComments(@PathVariable int id) throws NotFoundException {
		return srv.getOne(id).getComments().stream().map(item -> CommentShortDTO.from(item)).collect(Collectors.toList());
	}
	
	@GetMapping(path="/{id}/foto")
	@ApiOperation(value = "Recupera la foto de una pizza")
	public ResponseEntity<byte[]> getPhoto(@PathVariable int id) throws NotFoundException {
		var result = dao.findById(id);
		var img = result.get().getImage();
		
		if(result.isEmpty() || result.get().getImage() == null)
			return ResponseEntity.notFound().build();
	
		return ResponseEntity.ok().header("content-type", mimeTypeImage(img)).body(result.get().getImage());
	}
	
	@PutMapping(path="/{id}/foto")
	@ApiOperation(value = "Modifica la foto de una pizza")
	public ResponseEntity<byte[]> setPhoto(@PathVariable int id, @RequestBody byte[] file) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			return ResponseEntity.notFound().build();
		item.get().setImage(file);
		var result = dao.save(item.get());
		var img = result.getImage();
		return ResponseEntity.ok().header("content-type", mimeTypeImage(img)).body(result.getImage());
	}

	@DeleteMapping("/{id}/foto")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borra la foto de una pizza")
	public void deleteFoto(@PathVariable int id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		item.get().setImage(null);
		dao.save(item.get());
	}
	
	
	public String mimeTypeImage(byte[] img) {
		
		int[] array = new int[4];
		String toHex = "";
		String imgType = "";
		
		for (int i = 0; i < 3; i++) {
			array[i] = img[i];
			if (array[i] < 0) array[i] = array[i] + 256;
			toHex += (Integer.toHexString(array[i])).toUpperCase();
		}
		
		switch(toHex) {
			case "FFD8FF": 
				imgType = "image/jpg";
				break;
			case "89504E":
				imgType = "image/png";
				break;
			case "001":
				imgType = "image/x-icon";
				break;
			default:
				imgType = "image/*";
		}
		
		return imgType;
	}
}