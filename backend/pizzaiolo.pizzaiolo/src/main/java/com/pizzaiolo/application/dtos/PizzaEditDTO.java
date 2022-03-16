package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredient;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
//todo implementar swagger
//@ApiModel(value = "Pizzas editables", description = "Version editable de las pizzas.")
public class PizzaEditDTO {
	
	@JsonProperty("idPizza")
//	@ApiModelProperty(value = "Identificador de la pizza.")
	private int idPizza;
	
	@JsonProperty("idBase")
//	@ApiModelProperty(value = "Identificador de la base.")
		private int base;
	
	@JsonProperty("idSauce")
//	@ApiModelProperty(value = "Identificador de la salsa.")
		private int sauce;
	
	@JsonProperty("description")
//	@ApiModelProperty(value = "Descripción de la pizza.")
	private String description;
	
	@JsonProperty("netPrice")
//	@ApiModelProperty(value = "Precio neto de la pizza.")
	private BigDecimal netPrice;
	
	@JsonProperty("amount")
//	@ApiModelProperty(value = "Coste total de la pizza.")
	private BigDecimal amount;
	
	@JsonProperty("active")
//	@ApiModelProperty(value = "Pizza activa.")
	private boolean active;
	
	@JsonProperty("image")
//	@ApiModelProperty(value = "Imagen de la pizza.")
	private byte[] image;

	public static PizzaEditDTO from(Pizza source) {
		return new PizzaEditDTO(
				source.getIdPizza(), 
				source.getBase().getId(), 
				source.getSauce().getId(), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.getActive(),
				source.getImage());
						
	}

	public static Pizza from(PizzaEditDTO source) {
		return new Pizza(
				source.getIdPizza(), 
				new Ingredient(source.getBase()), 
				new Ingredient(source.getSauce()), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.isActive(),
				source.getImage());
	}
	
	
	
}
