package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pizzaorders database table.
 * 
 */
@Embeddable
public class PizzaorderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idOrder;

	@Column(insertable=false, updatable=false)
	private int idPizza;

	public PizzaorderPK() {
	}
	public PizzaorderPK(int idPizza, int idOrder) {
		super();
		this.idPizza = idPizza;
		this.idOrder = idOrder;
	}
	public int getIdOrder() {
		return this.idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdPizza() {
		return this.idPizza;
	}
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PizzaorderPK)) {
			return false;
		}
		PizzaorderPK castOther = (PizzaorderPK)other;
		return 
			(this.idOrder == castOther.idOrder)
			&& (this.idPizza == castOther.idPizza);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOrder;
		hash = hash * prime + this.idPizza;
		
		return hash;
	}
}