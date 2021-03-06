package com.pizzaiolo.authorization.models.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;

import com.pizzaiolo.authorization.models.entities.Address;
import com.pizzaiolo.authorization.models.entities.Coordinates;
import com.pizzaiolo.authorization.models.entities.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "UpdateUserParam", description = "Parameters required to update an user")
@Accessors(chain = true)
@Setter
@Getter
public class UpdateUserDto {
    @ApiModelProperty(notes = "User first name")
    private String firstName;

    @ApiModelProperty(notes = "User last name")
    private String lastName;

    @ApiModelProperty(notes = "User timezone")
    private String timezone;

    @ApiModelProperty(notes = "User gender")
    private String gender;

    private String avatar;

    @ApiModelProperty(notes = "Indicates if the will be enabled or not")
    private boolean enabled;

    @ApiModelProperty(notes = "Indicates if has confirmed his account")
    private boolean confirmed;

    @ApiModelProperty(notes = "Geographic location of the user")
    private Coordinates coordinates;
    
    private String Phone;

    //private Set<Role> roles;
}
