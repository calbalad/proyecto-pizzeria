package com.pizzaiolo.authorization.models.dtos;

import com.pizzaiolo.authorization.models.entities.Address;
import com.pizzaiolo.authorization.models.entities.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "CreateAddressParam", description = "Parameters required to create address")
@Accessors(chain = true)
@Setter
@Getter
public class CreateAddressDto {
    @ApiModelProperty(notes = "Name of the address", required = true)
    @NotBlank(message = "The name is required")
    private String name;

    @ApiModelProperty(notes = "Description of the street")
    private String street;

    @NotBlank
    private int number;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String location;
    
    @NotBlank
    private int postalCode;

    public Address toAddress() {
        return new Address()
            .setStreet(this.street)
            .setNumber(this.number)
            .setCity(this.city)
            .setLocation(this.location)
            .setPostalCode(this.postalCode)
            .setName(this.name);
    }
}
