package com.pizzaiolo.application.dtos;

import java.awt.Point;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.domains.entities.Pizzaorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class OrderStatusEditDTO {
	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("idUser")
	private String idUser;	
	@Convert(converter = Order.Status.class)
	@ApiModelProperty(value = "Estados de pedido.", allowableValues = "solicitado,elaborandose,preparado,enviado,recibido,cancelado")
	private String orderStatus;	
	private String idChef;
	private String idCourier;
	private Date deliveryDate;

	public static OrderStatusEditDTO from(Order source) {
		return new OrderStatusEditDTO(
				source.getIdOrder(),
				source.getIdUser(),
				source.getOrderStatus().getValue(),
				source.getIdChef(),
				source.getIdCourier(),
				source.getDeliveryDate()
						);
	}
	
	public Order updateStatus(Order target) {
		
		
		if(target.getOrderStatus() == Status.PEDIDO_SOLICITADO
				&& Order.Status.getEnum(orderStatus) == Status.PEDIDO_ELABORANDOSE 
				&& (target.getIdChef() == null)) {
			target.setIdChef(idChef);
			target.setOrderStatus(Order.Status.getEnum(orderStatus));
		}
		if(target.getOrderStatus() == Status.PEDIDO_ELABORANDOSE 
				&& Order.Status.getEnum(orderStatus) == Status.PEDIDO_PREPARADO) {
			target.setOrderStatus(Order.Status.getEnum(orderStatus));
		}
		if(target.getOrderStatus() == Status.PEDIDO_PREPARADO 
				&& Order.Status.getEnum(orderStatus) == Status.PEDIDO_ENVIADO 
				&& (target.getIdCourier() == null)) {
			target.setIdCourier(idCourier);
			target.setOrderStatus(Order.Status.getEnum(orderStatus));
		}
		if(target.getOrderStatus() == Status.PEDIDO_ENVIADO 
				&& Order.Status.getEnum(orderStatus) == Status.PEDIDO_RECIBIDO
				&& (target.getDeliveryDate() == null)) {
			target.setDeliveryDate(new Date());
			target.setOrderStatus(Order.Status.getEnum(orderStatus));
		}
		
		return target;
		
	}
	
	public Order cancelOrder(Order target) {
		
		if(target.getOrderStatus() == Status.PEDIDO_SOLICITADO)
		target.setOrderStatus(Status.PEDIDO_CANCELADO);
		
		return target;
		
	}
}
