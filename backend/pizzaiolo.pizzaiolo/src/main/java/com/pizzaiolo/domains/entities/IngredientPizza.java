package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the IngredientPizza database table.
 * 
 */
@Entity
@NamedQuery(name="IngredientPizza.findAll", query="SELECT i FROM IngredientPizza i")
public class IngredientPizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientPizzaPK id;

	private int quantity;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="idIngredient")
	private Ingredient ingredient;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="idPizza")
	private Pizza pizza;

	public IngredientPizza() {
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

}