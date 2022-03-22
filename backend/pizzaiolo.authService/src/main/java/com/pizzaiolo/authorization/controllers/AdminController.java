package com.pizzaiolo.authorization.controllers;

import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import com.pizzaiolo.authorization.models.dtos.CreateUserDto;
import com.pizzaiolo.authorization.models.entities.Role;
import com.pizzaiolo.authorization.models.entities.User;
import com.pizzaiolo.authorization.models.response.BadRequestResponse;
import com.pizzaiolo.authorization.models.response.InvalidDataResponse;
import com.pizzaiolo.authorization.models.response.SuccessResponse;
import com.pizzaiolo.authorization.models.response.UserResponse;
import com.pizzaiolo.authorization.services.interfaces.RoleService;
import com.pizzaiolo.authorization.services.interfaces.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.pizzaiolo.authorization.utils.Constants.FORBIDDEN_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.INVALID_DATA_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.ROLE_ADMIN;
import static com.pizzaiolo.authorization.utils.Constants.ROLE_NOT_FOUND_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_CREATE_ERROR;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_CREATE_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_CREATE_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_DELETE_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_DELETE_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_TAG_DESCRIPTION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_ADMIN_TAG_NAME;
import static com.pizzaiolo.authorization.utils.Constants.UNAUTHORIZED_MESSAGE;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = SWG_ADMIN_TAG_NAME, description = SWG_ADMIN_TAG_DESCRIPTION)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admins")
public class AdminController {
  private final RoleService roleService;

  private final UserService userService;

  public AdminController(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @ApiOperation(value = SWG_ADMIN_CREATE_OPERATION, response = BadRequestResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = SWG_ADMIN_CREATE_MESSAGE, response = UserResponse.class),
      @ApiResponse(code = 400, message = SWG_ADMIN_CREATE_ERROR, response = BadRequestResponse.class),
      @ApiResponse(code = 422, message = INVALID_DATA_MESSAGE, response = InvalidDataResponse.class),
  })
  @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
  @PostMapping(value = "")
  public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserDto createUserDto)
      throws ResourceNotFoundException {
    Role roleUser = roleService.findByName(ROLE_ADMIN);

    createUserDto.setRole(roleUser)
        .setConfirmed(true)
        .setEnabled(true);

    User user = userService.save(createUserDto);

    return ResponseEntity.ok(new UserResponse(user));
  }

  @ApiOperation(value = SWG_ADMIN_DELETE_OPERATION, response = SuccessResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = SWG_ADMIN_DELETE_MESSAGE, response = SuccessResponse.class),
      @ApiResponse(code = 401, message = UNAUTHORIZED_MESSAGE, response = BadRequestResponse.class),
      @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE, response = BadRequestResponse.class),
  })
  @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
