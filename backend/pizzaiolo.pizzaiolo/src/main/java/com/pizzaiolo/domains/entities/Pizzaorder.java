package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * The persistent class for the pizzaorders database table.
 * 
 */
@Entity
@Table(name="pizzaorders")
@NamedQuery(name="Pizzaorder.findAll", query="SELECT p FROM Pizzaorder p")
public class Pizzaorder extends EntityBase<Pizzaorder> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PizzaorderPK id;

	@NotNull
	//@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;

	@NotNull
	@Min(1)
	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder", insertable=false, updatable=false)
	private Order order;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="idPizza", insertable=false, updatable=false)
	private Pizza pizza;

	public Pizzaorder() {
	}

	public Pizzaorder(Pizza pizza, Order order) {
		super();
		this.pizza = pizza;
		this.order = order;
		this.id = new PizzaorderPK(pizza.getIdPizza(), order.getIdOrder());
	}
	
	public PizzaorderPK getId() {
		return this.id;
	}

	public Pizzaorder(@NotNull @Min(1) int quantity, Order order, Pizza pizza, int idOrder,String address, String idUser, int idPizza , BigDecimal amount) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.pizza = pizza;
		this.amount = amount;
		this.id = new PizzaorderPK(idPizza, idOrder);
		this.getOrder().setAddress(address);
	}
	
	public Pizzaorder(
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal amount,
			@NotNull @Min(1) int quantity, Order order, Pizza pizza) {
		super();
		this.amount = amount;
		this.quantity = quantity;
		this.order = order;
		this.pizza = pizza;
		this.id = new PizzaorderPK(pizza.getIdPizza(), order.getIdOrder());
	}
	
	public Pizzaorder(
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal amount,
			@NotNull @Min(1) int quantity, Order order, int idPizza) {
		super();
		this.amount = amount;
		this.quantity = quantity;
		this.order = order;
		this.id = new PizzaorderPK(idPizza, order.getIdOrder());
	}

	public void setId(PizzaorderPK id) {
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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizzaorder other = (Pizzaorder) obj;
		return Objects.equals(id, other.id);
	}
}