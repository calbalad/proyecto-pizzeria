package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.pizzaiolo.domains.core.entities.EntityBase;

/**
 * The persistent class for the IngredientPizza database table.
 * 
 */
@Entity
@NamedQuery(name = "IngredientPizza.findAll", query = "SELECT i FROM IngredientPizza i")
public class IngredientPizza extends EntityBase<IngredientPizza> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientPizzaPK id;

	// bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name = "idPizza")
	private Pizza pizza;

	// bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name = "idIngredient")
	private Ingredient ingredient;

	@Column(name="quantity")
	@Positive
	private int quantity = 1;

	public IngredientPizza() {
	}
	
	public IngredientPizza(IngredientPizzaPK id, Pizza pizza, Ingredient ingredient, @Positive int quantity) {
		super();
		this.id = new IngredientPizzaPK(pizza.getIdPizza(), ingredient.getId());
		this.pizza = pizza;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	public IngredientPizzaPK getId() {
		return this.id;
	}

	public void setId(IngredientPizzaPK id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
		IngredientPizza other = (IngredientPizza) obj;
		return Objects.equals(id, other.id);
	}
}