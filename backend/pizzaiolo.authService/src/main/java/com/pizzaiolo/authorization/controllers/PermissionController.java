package com.pizzaiolo.authorization.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.pizzaiolo.authorization.utils.Constants.FORBIDDEN_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.PERMISSION_NOT_FOUND_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_ITEM_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_ITEM_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_LIST_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_LIST_OPERATION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_TAG_DESCRIPTION;
import static com.pizzaiolo.authorization.utils.Constants.SWG_PERMISSION_TAG_NAME;
import static com.pizzaiolo.authorization.utils.Constants.UNAUTHORIZED_MESSAGE;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import com.pizzaiolo.authorization.models.entities.Permission;
import com.pizzaiolo.authorization.models.response.BadRequestResponse;
import com.pizzaiolo.authorization.models.response.PermissionListResponse;
import com.pizzaiolo.authorization.models.response.PermissionResponse;
import com.pizzaiolo.authorization.models.response.RoleListResponse;
import com.pizzaiolo.authorization.models.response.RoleResponse;
import com.pizzaiolo.authorization.models.response.SuccessResponse;
import com.pizzaiolo.authorization.services.interfaces.PermissionService;

@Api(tags = SWG_PERMISSION_TAG_NAME, description = SWG_PERMISSION_TAG_DESCRIPTION)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = SWG_PERMISSION_LIST_OPERATION, response = SuccessResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = SWG_PERMISSION_LIST_MESSAGE, response = RoleListResponse.class),
        @ApiResponse(code = 401, message = UNAUTHORIZED_MESSAGE, response = BadRequestResponse.class),
        @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE, response = BadRequestResponse.class),
    })
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<PermissionListResponse> all(){
        return ResponseEntity.ok(new PermissionListResponse(permissionService.findAll()));
    }

    @ApiOperation(value = SWG_PERMISSION_ITEM_OPERATION, response = SuccessResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = SWG_PERMISSION_ITEM_MESSAGE, response = RoleResponse.class),
        @ApiResponse(code = 401, message = UNAUTHORIZED_MESSAGE, response = BadRequestResponse.class),
        @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE, response = BadRequestResponse.class),
        @ApiResponse(code = 404, message = PERMISSION_NOT_FOUND_MESSAGE, response = BadRequestResponse.class),
    })
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> one(@PathVariable String id)
        throws ResourceNotFoundException {
        Optional<Permission> permission = permissionService.findById(id);

        if (permission.isEmpty()) {
            throw new ResourceNotFoundException(PERMISSION_NOT_FOUND_MESSAGE);
        }

        return ResponseEntity.ok(new PermissionResponse(permission.get()));
    }
}
