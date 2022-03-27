package com.pizzaiolo.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pizzaiolo.domains.contracts.repositories.OrderRepository;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;


class OrderServiceImplTest {

	List<Order> listado;
	OrderRepository dao;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<Order>();
		listado.add(new Order(1, "uno", "auno", "", new Date(2022-03-22), new BigDecimal(10.00)));
		listado.add(new Order(2, "uno", "auno", "", new Date(2022-03-22), new BigDecimal(10.00)));
		dao = mock(OrderRepository.class);
	} 

	@Test
	void testGetAll() {
		when(dao.findAll()).thenReturn(listado);
		var srv = new OrderServiceImpl(dao);
		
		var rslt = srv.getAll();
		
		assertNotNull(rslt);
		assertEquals(2, rslt.size());
	}

	@Test
	void testGetOne() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new OrderServiceImpl(dao);
		
		var rslt = srv.getOne(1);
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdOrder());
	}
	@Test
	void testGetOneNotFound() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.getOne(1));
	}

	@Test
	void testAdd() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new OrderServiceImpl(dao);
		
		var rslt = srv.add(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdOrder());
	}
	@Test
	void testAddNull() {
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.add(null));
	}
	@Test
	void testAddDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(DuplicateKeyException.class, () -> srv.add(listado.get(0)));
	}
	@Test
	void testAddInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.add(new Order(1)));
	}

	@Test
	void testChange() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new OrderServiceImpl(dao);
		
		var rslt = srv.change(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdOrder());
	}

	@Test
	void testChangeNull() {
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.change(null));
	}
	@Test
	void testChangeNotFound() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.change(listado.get(0)));
	}
	@Test
	void testChangeInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new OrderServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.change(new Order(1)));
	}

	@Test
	void testDelete() {
		doNothing().when(dao).deleteById(1);
		var srv = new OrderServiceImpl(dao);
		srv.delete(new Order(1));
		verify(dao).deleteById(1);
	}

	@Test
	void testDeleteById() {
		doNothing().when(dao).deleteById(1);
		var srv = new OrderServiceImpl(dao);
		srv.deleteById(1);
		verify(dao).deleteById(1);
	}
	
	@Test
	void testDeleteNull() {
		var srv = new OrderServiceImpl(dao);
		assertThrows(IllegalArgumentException.class, () -> srv.delete(null));
	}

}
