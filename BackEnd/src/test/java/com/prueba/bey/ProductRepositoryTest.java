package com.prueba.bey;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.prueba.bey.entity.Product;
import com.prueba.bey.repository.ProductRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	// JUnit test for saveEmployee
	@Test
	@Order(1)
	@Rollback(value = false)
	void saveProductTest() {
		Product product = Product.builder().description("Descripción test").buyValue(100000L).buyDate(LocalDate.now())
				.name("Ejemplo Test").serialNumber(1100665588L).build();
		productRepository.save(product);
		Assertions.assertThat(product.getIdProduct()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	void getProductTest() {
		Product product = productRepository.findById(1L).get();
		Assertions.assertThat(product.getIdProduct()).isEqualTo(1L);
	}

	@Test
	@Order(3)
	void getProductListTest() {
		List<Product> products = productRepository.findAll();
		Assertions.assertThat(products.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	void updateEProductTest() {
		Product product = productRepository.findById(1L).get();
		product.setName("Radio");
		Product productUpdated = productRepository.save(product);
		Assertions.assertThat(productUpdated.getName()).isEqualTo("Radio");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	void deleteProductTest() {
		Product product = productRepository.findById(1L).get();
		productRepository.delete(product);
		Product product1 = null;
		Optional<Product> optionalProduct = productRepository.findById(1L);
		if (optionalProduct.isPresent()) {
			product1 = optionalProduct.get();
		}
		Assertions.assertThat(product1).isNull();
	}

}
