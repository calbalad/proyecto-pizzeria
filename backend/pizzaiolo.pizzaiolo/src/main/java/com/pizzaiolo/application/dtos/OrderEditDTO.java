package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class OrderEditDTO {
	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("idUser")
	private String idUser;
	private String address;
	private BigDecimal amount;
	@ApiModelProperty(value = "Estados de pedido.", allowableValues = "Solicitado,Elaborandose,Preparado,Enviado,Recibido,Cancelado")
	private String orderStatus;
	private Date orderDate;
	private String idChef;
	private String idCourier;
	private Date deliveryDate;
	private String comment;
	@ApiModelProperty(value = "Lista de identificadores de pizzas.")
	private List<Integer> pizzas;

	public static OrderEditDTO from(Order source) {
		return new OrderEditDTO(
				source.getIdOrder(),
				source.getIdUser(),
				source.getAddress(),
				source.getAmount(),
				source.getOrderStatus(),
				source.getOrderDate(),
				source.getIdChef() == null ? null : source.getIdChef(),
				source.getIdCourier() == null ? null : source.getIdCourier(),
				source.getDeliveryDate() == null ? null : source.getDeliveryDate(),
				source.getComment() == null ? null : source.getComment(),
				source.getPizzaorders().stream().map(item -> item.getPizza().getIdPizza()).toList()
						);
	}

	public static Order from(OrderEditDTO source) {
		return new Order(
				source.getIdOrder(),
				source.getIdUser(),
				source.getAddress(),
				source.getAmount(),
				source.getOrderStatus(),
				source.getOrderDate(),
				source.getIdChef(),
				source.getIdCourier(),
				source.getDeliveryDate(),
				source.getComment()
				);
	}
	
	
	
}
