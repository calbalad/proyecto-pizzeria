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

import com.pizzaiolo.application.dtos.IngredientPizzaEditDTO;
import com.pizzaiolo.application.dtos.PizzaEditDTO;
import com.pizzaiolo.domains.contracts.services.PizzaService;
import com.pizzaiolo.domains.entities.Ingredient;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

@SpringBootTest
class PizzaResourceTest {
	List<PizzaEditDTO> listado;
	List<IngredientPizzaEditDTO> ingredientes1, ingredientes2, ingredientes3;
	
	@BeforeEach
	void setUp() throws Exception {
		
		ingredientes1 = new ArrayList<IngredientPizzaEditDTO>();
		ingredientes1.add(new IngredientPizzaEditDTO(11, 1));
		
		ingredientes2 = new ArrayList<IngredientPizzaEditDTO>();
		ingredientes2.add(new IngredientPizzaEditDTO(5, 1));
		ingredientes2.add(new IngredientPizzaEditDTO(11, 1));
		ingredientes2.add(new IngredientPizzaEditDTO(13, 1));
		ingredientes2.add(new IngredientPizzaEditDTO(20, 1));
		ingredientes2.add(new IngredientPizzaEditDTO(23, 1));

		
		
		
		listado = new ArrayList<PizzaEditDTO>();
		listado.add(new PizzaEditDTO(1, 1, 5, "Margarita Clasica", new BigDecimal(8), new BigDecimal(10), true, ingredientes1));
		listado.add(new PizzaEditDTO(6, 1, 8, "Carbonara Clasica", new BigDecimal(9), new BigDecimal(12), true, ingredientes2));

	}

	public static class IoCTestConfig {
		@Bean
		PizzaService getServicio() {
			return mock(PizzaService.class);
		}
		@Bean
		PizzaResource getRest() {
			return new PizzaResource();
		}
	}

	@Nested
	//@ContextConfiguration(classes = IoCTestConfig.class)
	@MockBean(PizzaService.class)
	class PruebasUnitarias {
		@Autowired
		PizzaService srv;
		
		@Autowired
		PizzaResource rest;
		
		@Test
		void testMock() {
			assertNotNull(srv);
			assertNotNull(rest);
		}
		
		@Test
		void testGetAll() {
			
			when(srv.getByProjection(PizzaEditDTO.class)).thenReturn(listado);
            var rslt = rest.getAll();
            assertNotNull(rslt);
            System.out.println(rslt);
		}

		@Test 
		void testGetOne() throws NotFoundException {

			
			when(srv.getOne(1)).thenReturn(PizzaEditDTO.from(listado.get(0)));

			var rslt = rest.getOneEdit(1, "edit"); 
			assertNotNull(rslt);
			assertEquals(1, rslt.getIdPizza());
		}

		@Test
		void testGetOneNotFound() throws NotFoundException {
			when(srv.getOne(1)).thenThrow(NotFoundException.class);
			
			assertThrows(NotFoundException.class, () -> rest.getOneEdit(1, "edit"));
		}

		@Test
		void testCreate() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));
			when(srv.getOne(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));
			when(srv.change(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));

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
			when(srv.getOne(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));
			when(srv.change(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));

			rest.update(1, listado.get(0));
			verify(srv).change(PizzaEditDTO.from(listado.get(0)));
		}

		@Test
		void testUpdateInvalidId() throws NotFoundException, InvalidDataException {
			assertThrows(InvalidDataException.class, () -> rest.update(0, listado.get(0)));
		}
		@Test
		void testUpdateNotFound() throws NotFoundException, InvalidDataException {
			when(srv.getOne(any())).thenReturn(PizzaEditDTO.from(listado.get(0)));
			when(srv.change(any())).thenThrow(NotFoundException.class);

			assertThrows(NotFoundException.class, () -> rest.update(1, listado.get(0)));
		}
	
		@Test
		void testUpdateInvalidData() throws NotFoundException, InvalidDataException {
			
			assertThrows(InvalidDataException.class, () -> rest.update(1, new PizzaEditDTO()));
		}
		
	}
}
