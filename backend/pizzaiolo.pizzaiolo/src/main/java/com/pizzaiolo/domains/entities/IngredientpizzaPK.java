package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ingredientpizzas database table.
 * 
 */
@Embeddable
public class IngredientpizzaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPizza;

	@Column(insertable=false, updatable=false)
	private int idIngredient;

	public IngredientpizzaPK() {
	}
	
	public IngredientpizzaPK(int idPizza, int idIngredient) {
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
		if (!(other instanceof IngredientpizzaPK)) {
			return false;
		}
		IngredientpizzaPK castOther = (IngredientpizzaPK)other;
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