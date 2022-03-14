package com.pizzaiolo.application.dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Order;

import lombok.Value;

@Value
public class OrderShortDTO {
	
	@JsonProperty("idOrder")
	private int idOrder;
	@JsonProperty("idUser")
	private String idUser;


	public static OrderShortDTO from(Order source) {
		return new OrderShortDTO(source.getIdOrder(), source.getIdUser());
	}

	
}
