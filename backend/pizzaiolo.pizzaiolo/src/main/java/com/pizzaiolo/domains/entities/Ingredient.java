package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Ingredient database table.
 * 
 */
@Entity
@NamedQuery(name="Ingredient.findAll", query="SELECT i FROM Ingredient i")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private BigDecimal price;

	private String type;

	//bi-directional many-to-one association to IngredientPizza
	@OneToMany(mappedBy="ingredient")
	private List<IngredientPizza> ingredientPizzas;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="idBase")
	private List<Pizza> basePizza;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="idSauce")
	private List<Pizza> salsaPizza;

	public Ingredient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<IngredientPizza> getIngredientPizzas() {
		return this.ingredientPizzas;
	}

	public void setIngredientPizzas(List<IngredientPizza> ingredientPizzas) {
		this.ingredientPizzas = ingredientPizzas;
	}

	public IngredientPizza addIngredientPizza(IngredientPizza ingredientPizza) {
		getIngredientPizzas().add(ingredientPizza);
		ingredientPizza.setIngredient(this);

		return ingredientPizza;
	}

	public IngredientPizza removeIngredientPizza(IngredientPizza ingredientPizza) {
		getIngredientPizzas().remove(ingredientPizza);
		ingredientPizza.setIngredient(null);

		return ingredientPizza;
	}

	public List<Pizza> getBasePizza() {
		return this.basePizza;
	}

	public void setBasePizza(List<Pizza> pizzas) {
		this.basePizza = pizzas;
	}

	public Pizza addBasePizza(Pizza pizza) {
		getBasePizza().add(pizza);
		pizza.setIdBase(this);

		return pizza;
	}

	public Pizza removeBasePizza(Pizza pizza) {
		getBasePizza().remove(pizza);
		pizza.setIdBase(null);

		return pizza;
	}

	public List<Pizza> getSalsaPizza() {
		return this.salsaPizza;
	}

	public void setSalsaPizza(List<Pizza> pizzas) {
		this.salsaPizza = pizzas;
	}

	public Pizza addSalsaPizza(Pizza pizza) {
		getSalsaPizza().add(pizza);
		pizza.setIdSauce(this);

		return pizza;
	}

	public Pizza removeSalsaPizza(Pizza pizza) {
		getSalsaPizza().remove(pizza);
		pizza.setIdSauce(null);

		return pizza;
	}

}