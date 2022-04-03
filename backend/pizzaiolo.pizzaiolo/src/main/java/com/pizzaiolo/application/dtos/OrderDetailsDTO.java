package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.domains.entities.Pizzaorder;

import lombok.Value;

@Value
public class OrderDetailsDTO {
	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("idUser")
	private String idUser;
	private String address;
	private BigDecimal amount;	
	private String orderStatus;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date orderDate;
	private String idChef;
	private String idCourier;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date deliveryDate;
	private String comment;
	private List<CestaEditDTO> pizzas;

	public static OrderDetailsDTO from(Order source) {
		return new OrderDetailsDTO(
				source.getIdOrder(),
				source.getIdUser(),
				source.getAddress(),
				source.getAmount(),
				source.getOrderStatus().getValue(),
				source.getOrderDate(),
				source.getIdChef() == null ? null : source.getIdChef(),
				source.getIdCourier() == null ? null : source.getIdCourier(),
				source.getDeliveryDate() == null ? null : source.getDeliveryDate(),
				source.getComment() == null ? null : source.getComment(),
				source.getPizzaorders().stream().map(item -> CestaEditDTO.from(item)).toList()
				//.stream().map(item -> CestaEditDTO.from(item)).toList()
								);
	}
	
}
