package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the Pizza database table.
 * 
 */
@Entity
@NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p")
public class Pizza extends EntityBase<Pizza> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPizza")
	private int idPizza;

	// bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name = "idBase")
	private Ingredient idBase;

	// bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name = "idSauce")
	private Ingredient idSauce;

	@Lob
	@Column(name="description")
	private String description;

	@Column(name="netPrice")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal netPrice;

	@Column(name="amount")
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;

	@Column(name="like")
	@PositiveOrZero
	private int like = 0;

	@Column(name="active")
	@NotNull
	private boolean active = true;

	@Lob
	@Column(name="image")
	private byte[] image = null;
	

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "pizza")
	private List<Comment> comments;

	// bi-directional many-to-one association to IngredientPizza
	@OneToMany(mappedBy = "pizza")
	private List<IngredientPizza> ingredientPizzas;

	// bi-directional many-to-one association to PizzaOrder
	@OneToMany(mappedBy = "pizza")
	private List<PizzaOrder> pizzaOrders;

	
	public Pizza() {
	}
	
	public Pizza(int idPizza) {
		super();
		this.idPizza = idPizza;
	}

	

	public Pizza(@NotNull int idPizza, Ingredient idBase, Ingredient idSauce, String description,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) @PositiveOrZero BigDecimal netPrice,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) @PositiveOrZero BigDecimal amount,
			@PositiveOrZero int like, @NotNull boolean active, byte[] image) {
		super();
		this.idPizza = idPizza;
		this.idBase = idBase;
		this.idSauce = idSauce;
		this.description = description;
		this.netPrice = netPrice;
		this.amount = amount;
		this.like = like;
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

	public int getLike() {
		return this.like;
	}

	public void setLike(int like) {
		this.like = like;
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

	public List<IngredientPizza> getIngredientPizzas() {
		return this.ingredientPizzas;
	}

	public void setIngredientPizzas(List<IngredientPizza> ingredientPizzas) {
		this.ingredientPizzas = ingredientPizzas;
	}

	public IngredientPizza addIngredientPizza(IngredientPizza ingredientPizza) {
		getIngredientPizzas().add(ingredientPizza);
		ingredientPizza.setPizza(this);

		return ingredientPizza;
	}

	public IngredientPizza removeIngredientPizza(IngredientPizza ingredientPizza) {
		getIngredientPizzas().remove(ingredientPizza);
		ingredientPizza.setPizza(null);

		return ingredientPizza;
	}

	public Ingredient getIdBase() {
		return this.idBase;
	}

	public void setIdBase(Ingredient idBase) {
		this.idBase = idBase;
	}

	public Ingredient getIdSauce() {
		return this.idSauce;
	}

	public void setIdSauce(Ingredient idSauce) {
		this.idSauce = idSauce;
	}

	public List<PizzaOrder> getPizzaOrders() {
		return this.pizzaOrders;
	}

	public void setPizzaOrders(List<PizzaOrder> pizzaOrders) {
		this.pizzaOrders = pizzaOrders;
	}

	public PizzaOrder addPizzaOrder(PizzaOrder pizzaOrder) {
		getPizzaOrders().add(pizzaOrder);
		pizzaOrder.setIdPizza(this);

		return pizzaOrder;
	}

	public PizzaOrder removePizzaOrder(PizzaOrder pizzaOrder) {
		getPizzaOrders().remove(pizzaOrder);
		pizzaOrder.setIdPizza(null);

		return pizzaOrder;
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
		return "Pizza [idPizza=" + idPizza + ", idBase=" + idBase + ", idSauce=" + idSauce + ", description="
				+ description + ", netPrice=" + netPrice + ", amount=" + amount + ", like=" + like + ", active="
				+ active + ", image=" + Arrays.toString(image) + "]";
	}
	
	

}