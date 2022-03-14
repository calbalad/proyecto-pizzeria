package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pizzaiolo.domains.core.entities.EntityBase;
import com.pizzaiolo.domains.entities.Ingredient.Type;
import com.pizzaiolo.domains.entities.Ingredient.TypeConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the ingredients database table.
 * 
 */
@Entity
@Table(name="ingredients")
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
	static class TypeConverter implements AttributeConverter<Type, String> {
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
	private String type;

	//bi-directional many-to-one association to Ingredientpizza
	@OneToMany(mappedBy="ingredient")
	private List<Ingredientpizza> ingredientpizzas;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="base")
	private List<Pizza> bases;

	//bi-directional many-to-one association to Pizza
	@OneToMany(mappedBy="sauce")
	private List<Pizza> sauces;

	public Ingredient() {
	}
	
	public Ingredient(@NotNull int id) {
		super();
		this.id = id;
	}

	public Ingredient(@NotNull int id, @NotBlank @Length(min = 1, max = 45) String name,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal price,
			String type) {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Ingredientpizza> getIngredientpizzas() {
		return this.ingredientpizzas;
	}

	public void setIngredientpizzas(List<Ingredientpizza> ingredientpizzas) {
		this.ingredientpizzas = ingredientpizzas;
	}

	public Ingredientpizza addIngredientpizza(Ingredientpizza ingredientpizza) {
		getIngredientpizzas().add(ingredientpizza);
		ingredientpizza.setIngredient(this);

		return ingredientpizza;
	}

	public Ingredientpizza removeIngredientpizza(Ingredientpizza ingredientpizza) {
		getIngredientpizzas().remove(ingredientpizza);
		ingredientpizza.setIngredient(null);

		return ingredientpizza;
	}

	public List<Pizza> getBases() {
		return this.bases;
	}

	public void setBases(List<Pizza> bases) {
		this.bases = bases;
	}

	public Pizza addBas(Pizza bas) {
		getBases().add(bas);
		bas.setBase(this);

		return bas;
	}

	public Pizza removeBas(Pizza bas) {
		getBases().remove(bas);
		bas.setBase(null);

		return bas;
	}

	public List<Pizza> getSauces() {
		return this.sauces;
	}

	public void setSauces(List<Pizza> sauces) {
		this.sauces = sauces;
	}

	public Pizza addSauce(Pizza sauce) {
		getSauces().add(sauce);
		sauce.setSauce(this);

		return sauce;
	}

	public Pizza removeSauce(Pizza sauce) {
		getSauces().remove(sauce);
		sauce.setSauce(null);

		return sauce;
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