package com.pizzaiolo.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pizzaiolo.domains.contracts.repositories.PizzaRepository;
import com.pizzaiolo.domains.contracts.services.PizzaService;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

@Service
public class PizzaServiceImpl implements PizzaService{
	private PizzaRepository dao;

	public PizzaServiceImpl(PizzaRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Pizza> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Pizza> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Pizza> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdPizzaIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdPizzaIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdPizzaIsNotNull(pageable, type);
	}

	@Override
	public Pizza getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Pizza add(Pizza item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPizza()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Pizza change(Pizza item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdPizza()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Pizza item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdPizza());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Order> getOrderUser(String id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
