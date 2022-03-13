package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pizza_order database table.
 * 
 */
@Embeddable
public class PizzaOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pizza_id", insertable=false, updatable=false)
	private int pizzaId;

	@Column(name="order_id", insertable=false, updatable=false)
	private int orderId;

	public PizzaOrderPK() {
	}
	public PizzaOrderPK(int pizzaId, int orderId) {
		super();
		this.pizzaId = pizzaId;
		this.orderId = orderId;
	}
	public int getPizzaId() {
		return this.pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public int getOrderId() {
		return this.orderId;
	}
	public void setwOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PizzaOrderPK)) {
			return false;
		}
		PizzaOrderPK castOther = (PizzaOrderPK)other;
		return 
			(this.pizzaId == castOther.pizzaId)
			&& (this.orderId == castOther.orderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pizzaId;
		hash = hash * prime + this.orderId;
		
		return hash;
	}
}