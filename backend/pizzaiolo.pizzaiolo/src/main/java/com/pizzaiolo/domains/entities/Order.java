package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.pizzaiolo.domains.core.entities.EntityBase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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

	private String address;

	private BigDecimal amount;

	@Lob
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;

	private String idChef;

	private String idCourier;

	private String idUser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private String orderStatus;

	//bi-directional many-to-one association to PizzaOrder
	@OneToMany(mappedBy="order")
	private List<PizzaOrder> pizzaOrders;

	public Order() {
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

}