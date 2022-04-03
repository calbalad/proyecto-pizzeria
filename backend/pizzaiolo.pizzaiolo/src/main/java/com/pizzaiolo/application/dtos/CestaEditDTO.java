package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.domains.entities.Pizzaorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import springfox.documentation.annotations.ApiIgnore;

@Value
public class CestaEditDTO {

	@JsonProperty("idPizza")
	private int idPizza;	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("amount")
	private BigDecimal amount;
	private String nombre;

	public static CestaEditDTO from(Pizzaorder source) {
		return new CestaEditDTO(
				source.getPizza().getIdPizza(),
				source.getOrder().getIdOrder(),
				source.getQuantity(),
				source.getAmount(),
				source.getPizza().getDescription()
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
				source.getPizza().getIdPizza(),
				source.getPizza().getAmount().multiply(BigDecimal.valueOf(source.getQuantity()))
				);
	}
	
}
