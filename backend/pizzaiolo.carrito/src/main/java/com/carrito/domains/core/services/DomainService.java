package com.carrito.domains.core.services;

import java.util.List;

import com.carrito.exceptions.DuplicateKeyException;
import com.carrito.exceptions.InvalidDataException;
import com.carrito.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();
	E getOne(K id) throws NotFoundException;
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	E change(E item) throws NotFoundException, InvalidDataException;
	void delete(E item);
	void deleteById(K id);
}
