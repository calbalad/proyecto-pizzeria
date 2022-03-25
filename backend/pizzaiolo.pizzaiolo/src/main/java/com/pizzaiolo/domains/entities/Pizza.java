package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.Valid;

import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the pizzas database table.
 * 
 */
@Entity
@Table(name = "pizzas")
@NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p")
public class Pizza extends EntityBase<Pizza> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPizza")
	private int idPizza;

	// bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name = "idBase")
	private Ingredient base;

	// bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name = "idSauce")
	private Ingredient sauce;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "netPrice")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal netPrice;

	@Column(name = "amount")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;

	@Column(name = "active")
	@NotNull
	private boolean active = true;

	@Lob
	@Column(name = "image")
	private byte[] image;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "pizza")
	private List<Comment> comments;

	// bi-directional many-to-one association to Ingredientpizza
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
	@Valid
	private List<Ingredientpizza> ingredientpizzas;

	// bi-directional many-to-one association to Pizzaorder
	@OneToMany(mappedBy = "pizza")
	private List<Pizzaorder> pizzaorders;

	public Pizza() {
		super();
		ingredientpizzas = new ArrayList<Ingredientpizza>();
	}

	public Pizza(int idPizza) {
		super();
		this.idPizza = idPizza;
	}

	public Pizza(@NotNull int idPizza, Ingredient base, Ingredient sauce, String description,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) @PositiveOrZero BigDecimal netPrice,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) @PositiveOrZero BigDecimal amount,
			@NotNull boolean active, byte[] image) {
		this();
		this.idPizza = idPizza;
		this.base = base;
		this.sauce = sauce;
		this.description = description;
		this.netPrice = netPrice;
		this.amount = amount;
		this.active = active;
		this.image = image;
	}

	public int getIdPizza() {
		return this.idPizza;
	}

	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public BigDecimal getNetPrice() {
		return this.netPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setPizza(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setPizza(null);

		return comment;
	}

	public List<Ingredientpizza> getIngredientpizzas() {
		return this.ingredientpizzas;
	}

	public void setIngredientpizzas(List<Ingredientpizza> ingredientpizzas) {
		this.ingredientpizzas = ingredientpizzas;
	}

	public Ingredientpizza addIngredientpizza(Ingredientpizza ingredientpizza) {
		getIngredientpizzas().add(ingredientpizza);
		ingredientpizza.setPizza(this);

		return ingredientpizza;
	}
	
	public Ingredientpizza addIngredientpizza(int idIngredient, int quantity) {
		var ingredientPizza = new Ingredientpizza(this, new Ingredient(idIngredient), quantity);
		getIngredientpizzas().add(ingredientPizza);

		return ingredientPizza;
	}

	public Ingredientpizza removeIngredientpizza(Ingredientpizza ingredientpizza) {
		getIngredientpizzas().remove(ingredientpizza);
		ingredientpizza.setPizza(null);

		return ingredientpizza;
	}
	
	public Ingredientpizza removeIngredientpizza(Ingredient ingredient) {
		var ingredientPizza = new Ingredientpizza(this, ingredient, 0);
		getIngredientpizzas().remove(ingredientPizza);

		return ingredientPizza;
	}

	public List<Pizzaorder> getPizzaorders() {
		return this.pizzaorders;
	}

	public void setPizzaorders(List<Pizzaorder> pizzaorders) {
		this.pizzaorders = pizzaorders;
	}

	public Ingredient getBase() {
		return this.base;
	}

	public void setBase(Ingredient base) {
		this.base = base;
	}

	public Ingredient getSauce() {
		return this.sauce;
	}

	public void setSauce(Ingredient sauce) {
		this.sauce = sauce;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPizza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return idPizza == other.idPizza;
	}

	@Override
	public String toString() {
		return "Pizza [idPizza=" + idPizza + ", idBase=" + base + ", idSauce=" + sauce + ", description=" + description
				+ ", netPrice=" + netPrice + ", amount=" + amount + ", active=" + active + ", image="
				+ Arrays.toString(image) + "]";
	}

}