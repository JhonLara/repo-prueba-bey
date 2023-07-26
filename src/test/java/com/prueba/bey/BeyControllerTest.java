package com.prueba.bey;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bey.controller.BeyController;
import com.prueba.bey.entity.Product;
import com.prueba.bey.service.impl.BeyServiceImpl;

@WebMvcTest(BeyController.class)
public class BeyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BeyServiceImpl beyService;

	@Test
	public void testProductList() throws Exception {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(1L, 1223655965L, "Test", "Ejemplo", LocalDate.now(), 1000000L, 1200L));
		Mockito.when(beyService.getProducts()).thenReturn(productList);

		String url = "/bey";

		mockMvc.perform(get(url)).andExpect(status().isOk());
	}

}
