package com.example.entity;

import java.math.BigDecimal;

public class Carrito {
	private String SessionId;
	private BigDecimal amount;
	private Integer quantity;
	private Integer idPizza;
	
	public Carrito(String SessionId,BigDecimal amount,Integer quantity,Integer idPizza) {
		this.SessionId = SessionId;
		this.amount = amount;
		this.quantity = quantity;
		this.idPizza = quantity;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(Integer idPizza) {
		this.idPizza = idPizza;
	}
	
}
