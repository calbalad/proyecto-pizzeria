package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Pizza;

import lombok.Value;

@Value
public class PizzaShortDTO {
	
	@JsonProperty("idPizza")
	private int idPizza;
	@JsonProperty("description")
	private String description;
	@JsonProperty("amount")
	private BigDecimal amount;

	public static PizzaShortDTO from(Pizza source) {
		return new PizzaShortDTO(
				source.getIdPizza(), 
				source.getDescription(), 
				source.getAmount());
	}
}
