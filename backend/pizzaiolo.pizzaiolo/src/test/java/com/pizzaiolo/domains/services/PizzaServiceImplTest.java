package com.pizzaiolo.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pizzaiolo.domains.contracts.repositories.PizzaRepository;
import com.pizzaiolo.domains.entities.Ingredient;
import com.pizzaiolo.domains.entities.Ingredient.Type;
import com.pizzaiolo.domains.entities.Pizza;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;


class PizzaServiceImplTest {

	List<Pizza> listado;
	PizzaRepository dao;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<Pizza>();
		listado.add(new Pizza(1, new Ingredient(1, "Clasica", new BigDecimal(10.00), Type.INGREDIENTE_BASE ), new Ingredient(5, "Tomate", new BigDecimal(10.00), Type.INGREDIENTE_SALSA ), "blabla", new BigDecimal(10.00), new BigDecimal(10.00), true, null));
		listado.add(new Pizza(1, new Ingredient(2, "Clasica", new BigDecimal(10.00), Type.INGREDIENTE_BASE ), new Ingredient(5, "Tomate", new BigDecimal(10.00), Type.INGREDIENTE_SALSA ), "blabla", new BigDecimal(10.00), new BigDecimal(10.00), true, null));
		dao = mock(PizzaRepository.class);
	} 

	
	
	
	
	@Test
	void testGetAll() {
		when(dao.findAll()).thenReturn(listado);
		var srv = new PizzaServiceImpl(dao);
		
		var rslt = srv.getAll();
		
		assertNotNull(rslt);
		assertEquals(2, rslt.size());
	}

	@Test
	void testGetOne() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new PizzaServiceImpl(dao);
		
		var rslt = srv.getOne(1);
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdPizza());
	}
	@Test
	void testGetOneNotFound() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.getOne(1));
	}

	@Test
	void testAdd() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new PizzaServiceImpl(dao);
		
		var rslt = srv.add(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdPizza());
	}
	@Test
	void testAddNull() {
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.add(null));
	}
	@Test
	void testAddDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(DuplicateKeyException.class, () -> srv.add(listado.get(0)));
	}
	@Test
	void testAddInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.add(new Pizza(1)));
	}

	@Test
	void testChange() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new PizzaServiceImpl(dao);
		
		var rslt = srv.change(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdPizza());
	}

	@Test
	void testChangeNull() {
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.change(null));
	}
	@Test
	void testChangeNotFound() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.change(listado.get(0)));
	}
	@Test
	void testChangeInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new PizzaServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.change(new Pizza(1)));
	}

	@Test
	void testDelete() {
		doNothing().when(dao).deleteById(1);
		var srv = new PizzaServiceImpl(dao);
		srv.delete(new Pizza(1));
		verify(dao).deleteById(1);
	}

	@Test
	void testDeleteById() {
		doNothing().when(dao).deleteById(1);
		var srv = new PizzaServiceImpl(dao);
		srv.deleteById(1);
		verify(dao).deleteById(1);
	}
	
	@Test
	void testDeleteNull() {
		var srv = new PizzaServiceImpl(dao);
		assertThrows(IllegalArgumentException.class, () -> srv.delete(null));
	}

}
