package com.pizzaiolo.application.dtos;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredientpizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Pizzas cortas", description = "Version corta de las pizzas.")
public class IngredientPizzaEditDTO {
	
	@JsonProperty("idIngredient")
	@ApiModelProperty(value = "Identificador del ingrediente.")
	private int idIngredient;
	
	@JsonProperty("quantity")
	@ApiModelProperty(value = "Cantidad del ingrediente.")
	@Positive
	private int quantity;


	public static IngredientPizzaEditDTO from(Ingredientpizza source) {
		return new IngredientPizzaEditDTO(
				source.getId().getIdIngredient(), 
				source.getQuantity());
	}
}
