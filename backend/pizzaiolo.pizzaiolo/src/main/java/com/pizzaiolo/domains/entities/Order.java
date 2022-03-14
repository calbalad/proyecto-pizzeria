package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order extends EntityBase<Order> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;

	@Length(min=2, max=500)
	@NotBlank
	private String address;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal amount;

	@Lob
	private String comment;

	@Generated(value = GenerationTime.ALWAYS)
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date deliveryDate;

	@Length(min=2, max=120)
	private String idChef;

	@Length(min=2, max=120)
	private String idCourier;

	@NotBlank
	@Length(min=2, max=120)
	private String idUser;

	@NotNull
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Generated(value = GenerationTime.INSERT)
	private Date orderDate;

	@NotNull
	@Convert(converter = OrderStatusConverter.class)
	private String orderStatus;
	
	public static enum OrderStatus {
	    PEDIDO_SOLICITADO("Solicitado"),
	    PEDIDO_ELABORANDOSE("Elaborandose"),
	    PEDIDO_PREPARADO("Preparado"),
	    PEDIDO_ENVIADO("Enviado"),
	    PEDIDO_RECIBIDO("Recibido"),
	    PEDIDO_CANCELADO("Cancelado");

	    String value;
	    
	    OrderStatus(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
		public static OrderStatus getEnum(String value) {
			switch (value) {
			case "Solicitado": return OrderStatus.PEDIDO_SOLICITADO;
			case "Elaborandose": return OrderStatus.PEDIDO_ELABORANDOSE;
			case "Preparado": return OrderStatus.PEDIDO_PREPARADO;
			case "Enviado": return OrderStatus.PEDIDO_ENVIADO;
			case "Recibido": return OrderStatus.PEDIDO_RECIBIDO;
			case "Cancelado": return OrderStatus.PEDIDO_CANCELADO;
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		}
	}
	@Converter
	private static class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
	    @Override
	    public String convertToDatabaseColumn(OrderStatus orderStatus) {
	        if (orderStatus == null) {
	            return null;
	        }
	        return orderStatus.getValue();
	    }
	    @Override
	    public OrderStatus convertToEntityAttribute(String value) {
	        if (value == null) {
	            return null;
	        }

	        return OrderStatus.getEnum(value);
	    }
	}
	
	//bi-directional many-to-one association to Pizzaorder
	@OneToMany(mappedBy="order")
	private List<Pizzaorder> pizzaorders = new ArrayList<>();

	public Order() {
	}
	
	public Order(int idOrder) {
		super();
		this.idOrder = idOrder;
	}
	

	
	public Order(int idOrder, @NotBlank @Length(min = 2, max = 120) String idUser,
			@Length(min = 2, max = 500) @NotBlank String address,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal amount,
			@NotNull String orderStat, @NotNull @PastOrPresent Date orderDate,
			@Length(min = 2, max = 120) String idChef, @Length(min = 2, max = 120) String idCourier,
			@PastOrPresent Date deliveryDate, String comment) {
		super();
		this.idOrder = idOrder;
		this.idUser = idUser;
		this.address = address;
		this.amount = amount;
		this.orderStatus = orderStat;
		this.orderDate = orderDate;
		this.idChef = idChef;
		this.idCourier = idCourier;
		this.deliveryDate = deliveryDate;
		this.comment = comment;
	}

	
	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getIdChef() {
		return this.idChef;
	}

	public void setIdChef(String idChef) {
		this.idChef = idChef;
	}

	public String getIdCourier() {
		return this.idCourier;
	}

	public void setIdCourier(String idCourier) {
		this.idCourier = idCourier;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Pizzaorder> getPizzaorders() {
		return this.pizzaorders;
	}

	public void setPizzaorders(List<Pizzaorder> pizzaorders) {
		this.pizzaorders = pizzaorders;
	}

	public Pizzaorder addPizzaorder(Pizzaorder pizzaorder) {
		getPizzaorders().add(pizzaorder);
		pizzaorder.setOrder(this);

		return pizzaorder;
	}

	public Pizzaorder removePizzaorder(Pizzaorder pizzaorder) {
		getPizzaorders().remove(pizzaorder);
		pizzaorder.setOrder(null);

		return pizzaorder;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idOrder);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return idOrder == other.idOrder;
	}


	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", address=" + address + ", orderStatus=" + orderStatus + "]";
	}
	

}