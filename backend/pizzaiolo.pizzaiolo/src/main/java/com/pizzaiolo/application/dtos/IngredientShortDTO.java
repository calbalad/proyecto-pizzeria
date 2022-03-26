package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Ingredientes cortos", description = "Version corta de los ingredientes.")
public class IngredientShortDTO {
	
//	@JsonProperty("idIngredient")
//	@ApiModelProperty(value = "Identificador del ingrediente.")
//	private int idPizza;
	
	@JsonProperty("name")
	@ApiModelProperty(value = "Nombre del ingrediente.")
	private String name;
	
	@JsonProperty("price")
	@ApiModelProperty(value = "Precio del ingrediente por unidad.")
	private BigDecimal price;

	public static IngredientShortDTO from(Ingredient source) {
		return new IngredientShortDTO(
//				source.getIdIngredient(), 
				source.getName(), 
				source.getPrice());
	}
}
