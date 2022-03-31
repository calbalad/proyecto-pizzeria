//package com.pizzaiolo.application.resources;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//
//import com.pizzaiolo.application.dtos.CommentDetailsDTO;
//import com.pizzaiolo.application.dtos.CommentEditDTO;
//import com.pizzaiolo.domains.contracts.services.CommentService;
//import com.pizzaiolo.exceptions.DuplicateKeyException;
//import com.pizzaiolo.exceptions.InvalidDataException;
//import com.pizzaiolo.exceptions.NotFoundException;
//
//@SpringBootTest
//class CommentResourceTest {
//	List<CommentEditDTO> listado;
//	List<CommentDetailsDTO> listado2;
//	@BeforeEach
//	void setUp() throws Exception {
//		listado = new ArrayList<CommentEditDTO>();
//		listado.add(new CommentEditDTO(1, null, "auno", 5, "comentario"));
//		listado.add(new CommentEditDTO(2, null, "blabla", 4, "comentarioprueba"));
//		
//		listado2 = new ArrayList<CommentDetailsDTO>();
//		listado2.add(new CommentDetailsDTO(1, null, "auno", 5, "comentario", 1));
//		listado2.add(new CommentDetailsDTO(2, null, "blabla", 4, "comentarioprueba", 1));
//	}
//
//	public static class IoCTestConfig {
//		@Bean
//		CommentService getServicio() {
//			return mock(CommentService.class);
//		}
//		@Bean
//		CommentResource getRest() {
//			return new CommentResource();
//		}
//	}
//
//	@Nested
//	//@ContextConfiguration(classes = IoCTestConfig.class)
//	@MockBean(CommentService.class)
//	class PruebasUnitarias {
//		@Autowired
//		CommentService srv;
//		
//		@Autowired
//		CommentResource rest;
//		
//		@Test
//		void testMock() {
//			assertNotNull(srv);
//			assertNotNull(rest);
//		}
//		@Test
//		void testGetAll() {
//			
//			when(srv.getByProjection(CommentEditDTO.class)).thenReturn(listado);
//            var rslt = rest.getAll();
//            assertNotNull(rslt);
//            System.out.println(rslt);
//            
////			when(srv.getByProjection(CommentEditDTO.class)).thenReturn(listado);
////			var rslt = rest.getAll();
////			System.out.println(rslt);
////			assertNotNull(rslt);
////			assertEquals(2, rslt.size());
//		}
//
//		@Test 
//		void testGetOne() throws NotFoundException {
//
//			
//			when(srv.getOne(1)).thenReturn(CommentDetailsDTO.from(listado2.get(0)));
//
//			var rslt = rest.getOne(1, ""); 
//			assertNotNull(rslt);
//			assertEquals(1, rslt.getIdComment());
//		}
//
//		@Test
//		void testGetOneNotFound() throws NotFoundException {
//			when(srv.getOne(1)).thenThrow(NotFoundException.class);
//			
//			assertThrows(NotFoundException.class, () -> rest.getOne(1, ""));
//		}
//
//		@Test
//		void testCreate() throws NotFoundException, DuplicateKeyException, InvalidDataException {
//			when(srv.add(any())).thenReturn(CommentEditDTO.from(listado.get(0)));
//
//			var rslt = rest.create(listado.get(0));
//			assertNotNull(rslt);
//			assertEquals(HttpStatus.CREATED, rslt.getStatusCode());
//		}
//		@Test
//		void testCreateDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
//			when(srv.add(any())).thenThrow(DuplicateKeyException.class);
//			
//			assertThrows(DuplicateKeyException.class, () -> rest.create(listado.get(0)));
//		}
//		@Test
//		void testCreateInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
//			when(srv.add(any())).thenThrow(InvalidDataException.class);
//			
//			assertThrows(InvalidDataException.class, () -> rest.create(listado.get(0)));
//		}
//
//		
//		@Test
//		void testDelete() throws NotFoundException, InvalidDataException {
//			doNothing().when(srv).deleteById(any());
//
//			rest.delete(1);
//			verify(srv).deleteById(1);
//		}
//	}
//
//}
