package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;


/**
 * The persistent class for the PizzaOrder database table.
 * 
 */
@Entity
@Table(name="pizza_order")
@NamedQuery(name="PizzaOrder.findAll", query="SELECT p FROM PizzaOrder p")
public class PizzaOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PizzaOrderPK id;
	
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;
	
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder", insertable=false, updatable=false)
	private Order order;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="idPizza", insertable=false, updatable=false)
	private Pizza pizza;

	public PizzaOrder() {
	}
	
	public PizzaOrder(Pizza pizza, Order order) {
		super();
		this.pizza = pizza;
		this.order = order;
		this.id = new PizzaOrderPK(pizza.getIdPizza(), order.getIdOrder());
	}
	
	public PizzaOrderPK getId() {
		return this.id;
	}
	
	public void setId(PizzaOrderPK id) {
		this.id = id;
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

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}