package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

import kk.domain.entities.Order;
import kk.domain.entities.Pizza;

import java.math.BigDecimal;


/**
 * The persistent class for the PizzaOrder database table.
 * 
 */
@Entity
@NamedQuery(name="PizzaOrder.findAll", query="SELECT p FROM PizzaOrder p")
public class PizzaOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order idOrder;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="idPizza")
	private Pizza idPizza;

	public PizzaOrder() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Order idOrder) {
		this.idOrder = idOrder;
	}

	public Pizza getIdPizza() {
		return this.idPizza;
	}

	public void setIdPizza(Pizza idPizza) {
		this.idPizza = idPizza;
	}

}