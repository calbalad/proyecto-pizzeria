package com.pizzaiolo.authorization.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.pizzaiolo.authorization.models.entities.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "UpdateRolParam", description = "Parameters required to update the Rol")
@Accessors(chain = true)
@Setter
@Getter
public class UpdateRolDto {
    @ApiModelProperty(notes = "New Role", required = true)
    private Role newRole;
}
