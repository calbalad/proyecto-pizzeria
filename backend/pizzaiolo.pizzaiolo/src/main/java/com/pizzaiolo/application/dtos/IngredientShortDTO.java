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
	
	@JsonProperty("idIngredient")
	@ApiModelProperty(value = "Identificador del ingrediente.")
	private int idIngredient;
	
	@JsonProperty("name")
	@ApiModelProperty(value = "Nombre del ingrediente.")
	private String name;
	
	@JsonProperty("price")
	@ApiModelProperty(value = "Precio del ingrediente por unidad.")
	private BigDecimal price;
	
	@JsonProperty("type")
	@ApiModelProperty(value = "Clasificación por tipo de ingrediente.", allowableValues = "base,salsa,otros")
	private String type;

	public static IngredientShortDTO from(Ingredient source) {
		return new IngredientShortDTO(
				source.getIdIngredient(),
				source.getName(), 
				source.getPrice(),
				source.getType() == null ? null : source.getType().getValue());						

	}
}
