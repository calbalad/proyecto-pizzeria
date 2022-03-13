package com.pizzaiolo.application.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;

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
				source.getComment() == null ? null : source.getComment()
				);
	}
	
}
