package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value

@ApiModel(value = "Ingredientes editables", description = "Version editable de los ingredientes.")
public class IngredientEditDTO {
	
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
	

	public static IngredientEditDTO from(Ingredient source) {
		return new IngredientEditDTO(
				source.getIdIngredient(), 
				source.getName(), 
				source.getPrice(), 
				source.getType() == null ? null : source.getType().getValue());						
	}

	public static Ingredient from(IngredientEditDTO source) {
		return new Ingredient(
				source.getIdIngredient(), 
				source.getName(), 
				source.getPrice(), 
				source.getType() == null ? null : Ingredient.Type.getEnum(source.getType()));
	}
	
	
	
	public Ingredient update(Ingredient target) {
		
		target.setName(name);
		target.setPrice(price);
		target.setType(type == null ? null : Ingredient.Type.getEnum(type));	
	
		return target;
	}
}
