package com.pizzaiolo.application.resources;

import static org.junit.jupiter.api.Assertions.*;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import com.pizzaiolo.application.dtos.IngredientEditDTO;
import com.pizzaiolo.domains.contracts.services.IngredientService;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

@SpringBootTest
class IngredientResourceTest {
	List<IngredientEditDTO> listado;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<IngredientEditDTO>();
		listado.add(new IngredientEditDTO(1, "peperoni", new BigDecimal(5), "otros"));
		listado.add(new IngredientEditDTO(2, "pimiento", new BigDecimal(4), "otros"));

	}

	public static class IoCTestConfig {
		@Bean
		IngredientService getServicio() {
			return mock(IngredientService.class);
		}
		@Bean
		IngredientResource getRest() {
			return new IngredientResource();
		}
	}

	@Nested
	//@ContextConfiguration(classes = IoCTestConfig.class)
	@MockBean(IngredientService.class)
	class PruebasUnitarias {
		@Autowired
		IngredientService srv;
		
		@Autowired
		IngredientResource rest;
		
		@Test
		void testMock() {
			assertNotNull(srv);
			assertNotNull(rest);
		}
		
		@Test
		void testGetAll() {
			
			when(srv.getByProjection(IngredientEditDTO.class)).thenReturn(listado);
            var rslt = rest.getAll();
            assertNotNull(rslt);
            System.out.println(rslt);
		}

		@Test 
		void testGetOne() throws NotFoundException {

			
			when(srv.getOne(1)).thenReturn(IngredientEditDTO.from(listado.get(0)));

			var rslt = rest.getOneEdit(1); 
			assertNotNull(rslt);
			assertEquals(1, rslt.getIdIngredient());
		}

		@Test
		void testGetOneNotFound() throws NotFoundException {
			when(srv.getOne(1)).thenThrow(NotFoundException.class);
			
			assertThrows(NotFoundException.class, () -> rest.getOneEdit(1));
		}

		@Test
		void testCreate() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenReturn(IngredientEditDTO.from(listado.get(0)));
			
			var rslt = rest.create(listado.get(0));
			assertNotNull(rslt);
			assertEquals(HttpStatus.CREATED, rslt.getStatusCode());
		}
		
		@Test
		void testCreateDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenThrow(DuplicateKeyException.class);
			
			assertThrows(DuplicateKeyException.class, () -> rest.create(listado.get(0)));
		}
		
		@Test
		void testCreateInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenThrow(InvalidDataException.class);
			
			assertThrows(InvalidDataException.class, () -> rest.create(listado.get(0)));
		}

		@Test
		void testUpdate() throws NotFoundException, InvalidDataException {
			when(srv.change(any())).thenReturn(IngredientEditDTO.from(listado.get(0)));

			rest.update(1, listado.get(0));
			verify(srv).change(IngredientEditDTO.from(listado.get(0)));
		}

		@Test
		void testUpdateInvalidId() throws NotFoundException, InvalidDataException {
			assertThrows(InvalidDataException.class, () -> rest.update(0, listado.get(0)));
		}
		@Test
		void testUpdateNotFound() throws NotFoundException, InvalidDataException {
			when(srv.change(any())).thenThrow(NotFoundException.class);

			assertThrows(NotFoundException.class, () -> rest.update(1, listado.get(0)));
		}
//		@Test
//		void testUpdateInvalidData() throws NotFoundException, InvalidDataException {
//			assertThrows(InvalidDataException.class, () -> rest.update(1, new IngredientEditDTO()));
//		}
//		
		
		
	}

}
