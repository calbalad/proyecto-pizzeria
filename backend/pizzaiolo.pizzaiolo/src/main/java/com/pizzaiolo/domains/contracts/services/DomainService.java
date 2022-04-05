package com.pizzaiolo.domains.contracts.services;

import java.util.List;

import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();
	E getOne(K id) throws NotFoundException;
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	E change(E item) throws NotFoundException, InvalidDataException;
	void delete(E item);
	void deleteById(K id);
	List<Order> getOrderUser(String id) throws NotFoundException;
}
