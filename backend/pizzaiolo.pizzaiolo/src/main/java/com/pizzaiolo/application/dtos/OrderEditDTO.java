package com.pizzaiolo.application.dtos;

import java.awt.Point;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.domains.entities.Pizzaorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class OrderEditDTO {
	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("idUser")
	private String idUser;
	private String address;
	private Date orderDate;
	private String comment;
	@ApiModelProperty(value = "Lista de identificadores de pizzas.")
	//private List<CarritoEditDTO> pizzas;
	private List<CestaEditDTO> pizzas;
	private BigDecimal amount;

	public static OrderEditDTO from(Order source) {
		return new OrderEditDTO(
				source.getIdOrder(),
				source.getIdUser(),
				source.getAddress(),
				source.getOrderDate(),
				source.getComment() == null ? null : source.getComment(),
				source.getPizzaorders().stream().map(item -> CestaEditDTO.from(item)).toList(),
				source.getAmount()
						);
	}

	public static Order from(OrderEditDTO source) {
		return new Order(
				source.getIdOrder(),
				source.getIdUser(),
				source.getAddress(),
				source.getComment(),
				source.getOrderDate(),
				source.getAmount()
				);
	}
	
	public Order updateAmount(Order target) {
	
		pizzas.stream().forEach(item -> target.addPizzaorder(new Pizzaorder(
				item.getAmount().multiply(BigDecimal.valueOf(item.getQuantity())),
				item.getQuantity(),
				target,
				item.getIdPizza()				
				)));
		
		return target;
		
	}
	
}
