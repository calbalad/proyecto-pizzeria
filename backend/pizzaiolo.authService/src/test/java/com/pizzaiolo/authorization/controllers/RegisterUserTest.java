package com.pizzaiolo.authorization.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;

import com.pizzaiolo.authorization.exceptions.GlobalExceptionHandler;
import com.pizzaiolo.authorization.services.interfaces.RoleService;
import com.pizzaiolo.authorization.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class RegisterUserTest {

	@Mock
	UserService userService;

	@Mock
	RoleService roleService;

	@InjectMocks
	AuthController authController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(authController).setControllerAdvice(new GlobalExceptionHandler())
				.build();
	}

	@Test
	public void testFailToRegisterUserCauseInvalidData() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", Matchers.hasKey("errors")))
				.andExpect(jsonPath("$.data.errors", Matchers.hasKey("firstName")))
				.andExpect(jsonPath("$.data.errors", Matchers.hasKey("lastName")))
				.andExpect(jsonPath("$.data.errors", Matchers.hasKey("timezone")))
				.andExpect(jsonPath("$.data.errors", Matchers.hasKey("confirmPassword")))
				.andExpect(jsonPath("$.data.errors", Matchers.hasKey("email")))
				.andReturn();

		System.out.println(result.getResponse().getContentAsString());
	}
}
