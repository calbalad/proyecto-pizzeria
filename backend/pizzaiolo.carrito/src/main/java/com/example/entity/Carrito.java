package com.example.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Carrito {
	private String session;
	private ArrayList<String> pizzas;
	private Integer quantity;
	private BigDecimal amount;
	private Integer idPizza;
	
	
	public Carrito(String session, ArrayList<String> carrito) {
		super();
		this.session = session;
		this.pizzas = carrito;
	}
	

	public String getSession() {
		return session;
	}


	public void setSession(String session) {
		this.session = session;
	}


	public ArrayList<String> getCarrito() {
		return pizzas;
	}


	public void setCarrito(ArrayList<String> carrito) {
		this.pizzas = carrito;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getIdPizza() {
		return idPizza;
	}


	public void setIdPizza(Integer idPizza) {
		this.idPizza = idPizza;
	}
	
	
	
	
}
