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
 * The persistent class for the Order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order extends EntityBase<Order> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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
	
	@NotNull
	@Convert(converter = OrderStatusConverter.class)
	private OrderStatus orderStatus;

	//bi-directional many-to-one association to PizzaOrder
	@OneToMany(mappedBy="order")
	private List<PizzaOrder> pizzaOrders = new ArrayList<>();

	public Order() {
	}
	
	
	public Order(int idOrder) {
		super();
		this.idOrder = idOrder;
	}
	
	public Order(int idOrder, @Length(min = 2, max = 500) @NotBlank String address,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 8, fraction = 2) BigDecimal amount,
			@NotBlank @Length(min = 2, max = 120) String idUser, @NotNull @PastOrPresent Date orderDate,
			@NotNull OrderStatus orderStatus) {
		super();
		this.idOrder = idOrder;
		this.address = address;
		this.amount = amount;
		this.idUser = idUser;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
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

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<PizzaOrder> getPizzaOrders() {
		return this.pizzaOrders;
	}

	public void setPizzaOrders(List<PizzaOrder> pizzaOrders) {
		this.pizzaOrders = pizzaOrders;
	}

	public PizzaOrder addPizzaOrder(PizzaOrder pizzaOrder) {
		getPizzaOrders().add(pizzaOrder);
		pizzaOrder.setOrder(this);

		return pizzaOrder;
	}

	public PizzaOrder removePizzaOrder(PizzaOrder pizzaOrder) {
		getPizzaOrders().remove(pizzaOrder);
		pizzaOrder.setOrder(null);

		return pizzaOrder;
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