package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the IngredientPizza database table.
 * 
 */
@Embeddable
public class IngredientPizzaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPizza;

	@Column(insertable=false, updatable=false)
	private int idIngredient;

	public IngredientPizzaPK() {
	}
	
	public IngredientPizzaPK(int idPizza, int idIngredient) {
		super();
		this.idPizza = idPizza;
		this.idIngredient = idIngredient;
	}
	
	public int getIdPizza() {
		return this.idPizza;
	}
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}
	public int getIdIngredient() {
		return this.idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IngredientPizzaPK)) {
			return false;
		}
		IngredientPizzaPK castOther = (IngredientPizzaPK)other;
		return 
			(this.idPizza == castOther.idPizza)
			&& (this.idIngredient == castOther.idIngredient);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPizza;
		hash = hash * prime + this.idIngredient;
		
		return hash;
	}
}