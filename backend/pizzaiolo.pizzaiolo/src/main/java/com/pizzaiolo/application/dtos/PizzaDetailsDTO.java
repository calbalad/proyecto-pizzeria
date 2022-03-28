package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.application.proxies.LikesProxy;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Pizzas detalladas", description = "Version detallada de las pizzas.")
public class PizzaDetailsDTO {
	
	@JsonProperty("idPizza")
	@ApiModelProperty(value = "Identificador de la pizza.")
	private int idPizza;
	
	@JsonProperty("idBase")
	@ApiModelProperty(value = "Identificador de la base.")
		private String base;

	@JsonProperty("idSauce")
	@ApiModelProperty(value = "Identificador de la salsa.")
		private String sauce;
	
	@JsonProperty("description")
	@ApiModelProperty(value = "Descripción de la pizza.")
	private String description;
	
	@JsonProperty("netPrice")
	@ApiModelProperty(value = "Precio neto de la pizza.")
	private BigDecimal netPrice;
	
	@JsonProperty("amount")
	@ApiModelProperty(value = "Coste total de la pizza.")
	private BigDecimal amount;
	
	@JsonProperty("active")
	@ApiModelProperty(value = "Pizza activa.")
	private boolean active;
	
	@JsonProperty("likes")
	@ApiModelProperty(value = "Cantidad de likes.")
	private String like;
	
	@JsonProperty("ingredientPizza")
	@ApiModelProperty(value = "Ingredientes.")
	private List<String> ingredientpizzas;
	
	

	public static PizzaDetailsDTO from(Pizza source, LikesProxy proxy) {
		return new PizzaDetailsDTO(
				source.getIdPizza(),
				source.getBase().getName(), 
				source.getSauce().getName(), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.getActive(),
				proxy.getLikes(source.getIdPizza()),
				source.getIngredientpizzas().stream().map(item -> item.getIngredient().getName()).sorted().toList()
				);
	}
}
