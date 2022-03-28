package com.pizzaiolo.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pizzaiolo.domains.contracts.repositories.CommentRepository;
import com.pizzaiolo.domains.entities.Comment;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;


class CommentServiceImplTest {

	List<Comment> listado;
	CommentRepository dao;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<Comment>();
		listado.add(new Comment(1, "uno", "auno"));
		listado.add(new Comment(2, "dos", "ados"));
		dao = mock(CommentRepository.class);
	} 

	@Test
	void testGetAll() {
		when(dao.findAll()).thenReturn(listado);
		var srv = new CommentServiceImpl(dao);
		
		var rslt = srv.getAll();
		
		assertNotNull(rslt);
		assertEquals(2, rslt.size());
	}

	@Test
	void testGetOne() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new CommentServiceImpl(dao);
		
		var rslt = srv.getOne(1);
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdComment());
	}
	@Test
	void testGetOneNotFound() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.getOne(1));
	}

	@Test
	void testAdd() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new CommentServiceImpl(dao);
		
		var rslt = srv.add(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdComment());
	}
	@Test
	void testAddNull() {
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.add(null));
	}
	@Test
	void testAddDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(DuplicateKeyException.class, () -> srv.add(listado.get(0)));
	}
	@Test
	void testAddInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.add(new Comment(1)));
	}

	@Test
	void testChange() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new CommentServiceImpl(dao);
		
		var rslt = srv.change(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdComment());
	}

	@Test
	void testChangeNull() {
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.change(null));
	}
	@Test
	void testChangeNotFound() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.change(listado.get(0)));
	}
	@Test
	void testChangeInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new CommentServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.change(new Comment(1)));
	}

	@Test
	void testDelete() {
		doNothing().when(dao).deleteById(1);
		var srv = new CommentServiceImpl(dao);
		srv.delete(new Comment(1));
		verify(dao).deleteById(1);
	}

	@Test
	void testDeleteById() {
		doNothing().when(dao).deleteById(1);
		var srv = new CommentServiceImpl(dao);
		srv.deleteById(1);
		verify(dao).deleteById(1);
	}
	
	@Test
	void testDeleteNull() {
		var srv = new CommentServiceImpl(dao);
		assertThrows(IllegalArgumentException.class, () -> srv.delete(null));
	}
}

