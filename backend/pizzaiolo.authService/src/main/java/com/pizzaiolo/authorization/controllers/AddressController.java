package com.pizzaiolo.authorization.controllers;

import static com.pizzaiolo.authorization.utils.Constants.FORBIDDEN_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.INVALID_DATA_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_CREATE_ERROR;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_CREATE_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_CREATE_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_DELETE_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_DELETE_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_TAG_DESCRIPTION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADDRESS_TAG_NAME;
import static com.pizzaiolo.authorization.utils.Constants.UNAUTHORIZED_MESSAGE;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.pizzaiolo.authorization.services.interfaces.UserService;
import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import com.pizzaiolo.authorization.models.dtos.CreateAddressDto;
import com.pizzaiolo.authorization.models.entities.Address;
import com.pizzaiolo.authorization.models.entities.User;
import com.pizzaiolo.authorization.models.response.BadRequestResponse;
import com.pizzaiolo.authorization.models.response.InvalidDataResponse;
import com.pizzaiolo.authorization.models.response.SuccessResponse;
import com.pizzaiolo.authorization.models.response.UserListResponse;
import com.pizzaiolo.authorization.models.response.UserResponse;
import com.pizzaiolo.authorization.services.interfaces.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = SWG_ADDRESS_TAG_NAME, description = SWG_ADDRESS_TAG_DESCRIPTION)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
@Validated
public class AddressController {
	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@ApiOperation(value = SWG_ADDRESS_CREATE_OPERATION, response = BadRequestResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = SWG_ADDRESS_CREATE_MESSAGE, response = UserResponse.class),
			@ApiResponse(code = 400, message = SWG_ADDRESS_CREATE_ERROR, response = BadRequestResponse.class),
			@ApiResponse(code = 422, message = INVALID_DATA_MESSAGE, response = InvalidDataResponse.class), })
	@PreAuthorize("hasAuthority('update:user')")
	@PostMapping(value = "/{idUser}")
	public ResponseEntity<Object> create(@PathVariable String idUser, @RequestBody CreateAddressDto createAddressDto,
			Principal principal) throws ResourceNotFoundException {
		User user = (User) principal;
		return ResponseEntity.ok().body(addressService.save(idUser, createAddressDto));

	}

	@ApiOperation(value = SWG_ADDRESS_DELETE_OPERATION, response = SuccessResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = SWG_ADDRESS_DELETE_MESSAGE, response = SuccessResponse.class),
			@ApiResponse(code = 401, message = UNAUTHORIZED_MESSAGE, response = BadRequestResponse.class),
			@ApiResponse(code = 403, message = FORBIDDEN_MESSAGE, response = SuccessResponse.class), })
	@PreAuthorize("hasAuthority('update:user')")
	@DeleteMapping("/{idAddress}")
	public ResponseEntity<Void> delete(@PathVariable String idAddress) {
		addressService.delete(idAddress);

		return ResponseEntity.noContent().build();
	}

}
