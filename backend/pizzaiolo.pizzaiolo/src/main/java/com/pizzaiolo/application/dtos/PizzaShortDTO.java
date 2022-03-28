package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Pizzas cortas", description = "Version corta de las pizzas.")
public class PizzaShortDTO {
	
	@JsonProperty("idPizza")
	@ApiModelProperty(value = "Identificador de la pizza.")
	private int idPizza;
	
	@JsonProperty("description")
	@ApiModelProperty(value = "Descripción de la pizza.")
	private String description;
	
	@JsonProperty("amount")
	@ApiModelProperty(value = "Coste total de la pizza.")
	private BigDecimal amount;

	public static PizzaShortDTO from(Pizza source) {
		return new PizzaShortDTO(
				source.getIdPizza(), 
				source.getDescription(),
				source.getAmount());
	}
}
