package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Ingredient;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value

@ApiModel(value = "Pizzas editables", description = "Version editable de las pizzas.")
public class PizzaEditDTO {
	
	@JsonProperty("idPizza")
	@ApiModelProperty(value = "Identificador de la pizza.")
	private int idPizza;
	
	@JsonProperty("idBase")
	@ApiModelProperty(value = "Identificador de la base.")
		private int base;
	
	@JsonProperty("idSauce")
	@ApiModelProperty(value = "Identificador de la salsa.")
		private int sauce;
	
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
	
	@JsonProperty("ingredientPizza")
	@ApiModelProperty(value = "Ingredientes.")
	private List<IngredientPizzaEditDTO> ingredientpizzas;

	public static PizzaEditDTO from(Pizza source) {
		return new PizzaEditDTO(
				source.getIdPizza(), 
				source.getBase().getIdIngredient(), 
				source.getSauce().getIdIngredient(), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.getActive(),
				source.getIngredientpizzas().stream().map(item -> IngredientPizzaEditDTO.from(item)).toList()
				);
	}

	public static Pizza from(PizzaEditDTO source) {
		return new Pizza(
				source.getIdPizza(), 
				new Ingredient(source.getBase()), 
				new Ingredient(source.getSauce()), 
				source.getDescription(), 
				source.getNetPrice(),
				source.getAmount(),
				source.isActive()
				);
	}
	
	
	public Pizza update(Pizza target) {
		actualizarPropiedadesEntidad(target);
		borrarIngredientesSobrantes(target);
		incorporarNuevosIngredientes(target);
		return target;
		
	}
		
	private void actualizarPropiedadesEntidad(Pizza target) {
		if (target.getBase().getIdIngredient() != base)
			target.setBase(new Ingredient(base));
		
		if(target.getSauce().getIdIngredient() != sauce)
			target.setSauce(new Ingredient(sauce));
		
		if(target.getDescription() != description)
			target.setDescription(description);
		
		if(target.getNetPrice() != netPrice)
			target.setNetPrice(netPrice);
		
		if(target.getAmount() != amount)
			target.setAmount(amount);
		
		if(target.getActive() != active)
			target.setActive(active);
	}
		
	private void borrarIngredientesSobrantes(Pizza target) {
		var delIngredientPizzas = target.getIngredientpizzas().stream()
				.filter(item -> !ingredientpizzas.contains(item.getIngredient().getIdIngredient()))
				.toList();
		delIngredientPizzas.forEach(item -> target.removeIngredientpizza(item));
	}
		
	private void incorporarNuevosIngredientes(Pizza target) {	
		ingredientpizzas.stream()
		.filter(ingredientpizzaeditDTO -> !target.getIngredientpizzas().stream().anyMatch(ingredientpizza -> ingredientpizza.getId().getIdIngredient() == ingredientpizzaeditDTO.getIdIngredient()))
		.forEach(ingredientpizzaeditDTO -> target.addIngredientpizza(ingredientpizzaeditDTO.getIdIngredient(),ingredientpizzaeditDTO.getQuantity()));
	}
}
