package com.pizzaiolo.domains.contracts.services;

import java.util.List;

import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;

public interface OrderService extends ProjectionDomainService <Order, Integer>{
//	List<Order> findByStatus(Status status);
}
