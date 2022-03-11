package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;



import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Pizza database table.
 * 
 */
@Entity
@NamedQuery(name="Pizza.findAll", query="SELECT p FROM Pizza p")
public class Pizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPizza;

	private boolean active;

	private BigDecimal amount;

	@Lob
	private String description;

	@Lob
	private byte[] image;

	private int like;

	private BigDecimal netPrice;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="pizza")
	private List<Comment> comments;

	//bi-directional many-to-one association to IngredientPizza
	@OneToMany(mappedBy="pizza")
	private List<IngredientPizza> ingredientPizzas;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="idBase")
	private Ingredient idBase;

	//bi-directional many-to-one association to Ingredient
	@ManyToOne
	@JoinColumn(name="idSauce")
	private Ingredient idSauce;

	//bi-directional many-to-one association to PizzaOrder
	@OneToMany(mappedBy="pizza")
	private List<PizzaOrder> pizzaOrders;

	public Pizza() {
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
		pizzaOrder.setPizza(this);

		return pizzaOrder;
	}

	public PizzaOrder removePizzaOrder(PizzaOrder pizzaOrder) {
		getPizzaOrders().remove(pizzaOrder);
		pizzaOrder.setPizza(null);

		return pizzaOrder;
	}

}