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

import com.pizzaiolo.application.dtos.OrderDetailsDTO;
import com.pizzaiolo.application.dtos.OrderEditDTO;
import com.pizzaiolo.application.dtos.OrderStatusEditDTO;
import com.pizzaiolo.domains.contracts.services.OrderService;
import com.pizzaiolo.domains.entities.Ingredient.Type;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

@SpringBootTest
class OrderResourceTest {
	List<OrderEditDTO> listado;
	List<OrderDetailsDTO> listado2; 
	List<OrderStatusEditDTO> listado3;
	
	@BeforeEach
	void setUp() throws Exception {
//		listado = new ArrayList<OrderEditDTO>();
//		listado.add(new OrderEditDTO(1, "", "auno", Status.PEDIDO_ELABORANDOSE,  new Date(2022-03-22),  "", "",  new Date(2022-03-22), "", new CarritoEditDTO(1,2)  , new BigDecimal(5.00)));
//		listado.add(new OrderEditDTO(2, "", "auno", Status.PEDIDO_ELABORANDOSE,  new Date(2022-03-22),  "", "",  new Date(2022-03-22), "", new CarritoEditDTO(1,2)  , new BigDecimal(5.00)));
//		
		listado2 = new ArrayList<OrderDetailsDTO>();
//		listado2.add(new OrderDetailsDTO(1, "", "auno", new BigDecimal(5.00), "",  new Date(2022-03-22),  "", "",  new Date(2022-03-22), ""));
//		listado2.add(new OrderDetailsDTO(2, "", "auno", new BigDecimal(5.00), "",  new Date(2022-03-22),  "", "",  new Date(2022-03-22), ""));
//		
		listado3 = new ArrayList<OrderStatusEditDTO>();
		listado3.add(new OrderStatusEditDTO(1, "", "auno",  "",  "",  new Date(2022-03-22)));
		listado3.add(new OrderStatusEditDTO(2, "", "auno",  "",  "",  new Date(2022-03-22)));
	}

	public static class IoCTestConfig {
		@Bean
		OrderService getServicio() {
			return mock(OrderService.class);
		}
		@Bean
		OrderResource getRest() {
			return new OrderResource();
		}
	}

	@Nested
	//@ContextConfiguration(classes = IoCTestConfig.class)
	@MockBean(OrderService.class)
	class PruebasUnitarias {
		@Autowired
		OrderService srv;
		
		@Autowired
		OrderResource rest;
		
		@Test
		void testMock() {
			assertNotNull(srv);
			assertNotNull(rest);
		}
		@Test
		void testGetAll() {
			
//			when(srv.getByProjection(OrderEditDTO.class)).thenReturn(listado);
//            var rslt = rest.getAll();
//            assertNotNull(rslt);
//            System.out.println(rslt);
//            
//			when(srv.getByProjection(OrderEditDTO.class)).thenReturn(listado);
//			var rslt = rest.getAll();
//			System.out.println(rslt);
//			assertNotNull(rslt);
//			assertEquals(2, rslt.size());
		}

		@Test 
		void testGetOne() throws NotFoundException {

		
//			when(srv.getOne(1)).thenReturn(OrderDetailsDTO.from(listado2.get(0)));

			var rslt = rest.getOneDetails(1, ""); 
			assertNotNull(rslt);
			assertEquals(1, rslt.getIdOrder());
		}

		@Test
		void testGetOneNotFound() throws NotFoundException {
			when(srv.getOne(1)).thenThrow(NotFoundException.class);
			
			assertThrows(NotFoundException.class, () -> rest.getOneDetails(1, ""));
		}

		@Test
		void testCreate() throws NotFoundException, DuplicateKeyException, InvalidDataException {
			when(srv.add(any())).thenReturn(OrderEditDTO.from(listado.get(0)));

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
		void testcancelOrder() throws NotFoundException, InvalidDataException {
			doNothing().when(srv).deleteById(any());

			rest.cancelOrder(1, listado3.get(0));
			verify(srv).deleteById(1);
		}
	}

}
