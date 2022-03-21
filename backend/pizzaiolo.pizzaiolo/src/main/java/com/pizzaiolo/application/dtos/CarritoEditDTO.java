package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.domains.entities.Pizzaorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class CarritoEditDTO {

	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("idPizza")
	private int idPizza;
	private Pizzaorder source;
	private Pizza pizza;

	public static CarritoEditDTO from(Pizzaorder source) {
		return new CarritoEditDTO(
				source.getQuantity(),
				source.getPizza().getIdPizza(),
				source,
				source.getPizza()
				);
	}
	
	public static Pizzaorder from(Pizzaorder source, Order order) {
		return new Pizzaorder(
				source.getQuantity(),
				order,
				source.getPizza(),
				order.getIdOrder(),
				order.getAddress(),
				order.getIdUser(),
				source.getPizza().getIdPizza()
				);
	}
	
}
