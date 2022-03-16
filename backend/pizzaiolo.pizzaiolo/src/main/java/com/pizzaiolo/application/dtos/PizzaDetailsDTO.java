package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredient;
import com.pizzaiolo.domains.entities.Pizza;

import lombok.Value;

@Value
//todo implementar swagger
//@ApiModel(value = "Pizzas detalladas", description = "Version detallada de las pizzas.")
public class PizzaDetailsDTO {
	
	@JsonProperty("idPizza")
//	@ApiModelProperty(value = "Identificador de la pizza.")
	private int idPizza;
	
	@JsonProperty("idBase")
//	@ApiModelProperty(value = "Identificador de la base.")
		private String base;

	@JsonProperty("idSauce")
//	@ApiModelProperty(value = "Identificador de la salsa.")
		private String sauce;
	
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

	public static PizzaDetailsDTO from(Pizza source) {
		return new PizzaDetailsDTO(
				source.getIdPizza(),
				source.getBase().getName(), 
				source.getSauce().getName(), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.getActive(),
				source.getImage());
	}
}
