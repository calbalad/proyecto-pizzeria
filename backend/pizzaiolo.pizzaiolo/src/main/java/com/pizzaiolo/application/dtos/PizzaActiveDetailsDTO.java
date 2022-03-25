package com.pizzaiolo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Pizzas detalladas", description = "Version detallada de las pizzas.")
public class PizzaActiveDetailsDTO {

	@JsonProperty("active")
	@ApiModelProperty(value = "Pizza activa.")
	private boolean active;

	public static PizzaActiveDetailsDTO from(Pizza source) {
		return new PizzaActiveDetailsDTO(
				source.getActive());
	}
}
