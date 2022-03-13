package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the Ingredient database table.
 * 
 */
@Entity
@NamedQuery(name="Ingredient.findAll", query="SELECT i FROM Ingredient i")
public class Ingredient extends EntityBase<Ingredient> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum Type {
	    INGREDIENTE_BASE("Base"),
	    INGREDIENTE_SALSA("Salsa"),
	    INGREDIENTE_OTROS("Otros");

	    String value;
	    
	    Type(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
		public static Type getEnum(String value) {
			switch (value) {
			case "Base": return Type.INGREDIENTE_BASE;
			case "Salsa": return Type.INGREDIENTE_SALSA;
			case "Otros": return Type.INGREDIENTE_OTROS;
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		}
	}
	@Converter
	private static class TypeConverter implements AttributeConverter<Type, String> {
	    @Override
	    public String convertToDatabaseColumn(Type type) {
	        if (type == null) {
	            return null;
	        }
	        return type.getValue();
	    }
	    @Override
	    public Type convertToEntityAttribute(String value) {
	        if (value == null) {
	            return null;
	        }

	        return Type.getEnum(value);
	    }
	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotBlank
	@Length(min = 1, max = 45)
	@Column(name="name", unique = true)
	private String name;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	@Column(name="price")
	private BigDecimal price;

	@NotNull
	@Convert(converter = TypeConverter.class)
	private Type type;

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
	
	public Ingredient(@NotNull int id) {
		super();
		this.id = id;
	}

	public Ingredient(@NotNull int id, @NotBlank @Length(min = 1, max = 45) String name,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal price,
			Type type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
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

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
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
		Ingredient other = (Ingredient) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}
}