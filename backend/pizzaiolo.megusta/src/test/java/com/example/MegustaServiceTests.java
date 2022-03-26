//package com.example;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.http.HttpStatus;
//
//import com.pizzaiolo.MeGustaService;
//
//
//
//
//@SpringBootTest
//class MegustaServiceTests {
//	MeGustaService megusta1 = new MeGustaService();
//	@BeforeEach
//	void setUp() throws Exception {
//	
//	}
//
//	public static class IoCTestConfig {
//		@Bean
//		MeGustaService getServicio() {
//			return mock(MeGustaService.class);
//		}
//		@Bean
//		MeGustaService getRest() {
//			return new MeGustaService();
//		}
//	}
//
//	@Nested
//	//@ContextConfiguration(classes = IoCTestConfig.class)
//	@MockBean(MeGustaService.class)
//	class PruebasUnitarias {
//		@Autowired
//		MeGustaService srv;
//		
//		@Autowired
//		MeGustaService rest;
//		
//		@Test
//		void testMock() {
//			assertNotNull(srv);
//			assertNotNull(rest);
//		}
//
//
//		@Test 
//		void testGetOne() {
//
//			
//			when(srv.get(1)).thenReturn(MeGustaService.from(megusta1.get()));
//
//			var rslt = rest.get(1); 
//			assertNotNull(rslt);
//			assertEquals(1, rslt.get());
//		}
//
//		@Test
//		void testGetOneNotFound()  {
//			when(srv.get(1)).thenThrow(NotFoundException.class);
//			
//			assertThrows(NotFoundException.class, () -> rest.get(1, ""));
//		}
//
//		@Test
//		void testCreate() throws NotFoundException, DuplicateKeyException {
//			when(srv.addLike(any())).thenReturn(MeGustaService.from(megusta1.get(0)));
//
//			var rslt = rest.create(megusta1.get(0));
//			assertNotNull(rslt);
//			assertEquals(HttpStatus.CREATED, rslt.getStatusCode());
//		}
//		@Test
//		void testCreateDuplicateKey() throws NotFoundException, DuplicateKeyException {
//			when(srv.addLike(any())).thenThrow(DuplicateKeyException.class);
//			
//			assertThrows(DuplicateKeyException.class, () -> rest.create(listado.get(0)));
//		}
//	
//		
//		@Test
//		void testDelete() throws NotFoundException {
//			doNothing().when(srv).deleteById(any());
//
//			rest.deleteLike(1);
//			verify(srv).deleteById(1);
//		}
//	}
//
//}
