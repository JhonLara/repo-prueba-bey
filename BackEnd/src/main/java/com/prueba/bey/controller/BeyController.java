package com.prueba.bey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.bey.entity.BeyException;
import com.prueba.bey.entity.Product;
import com.prueba.bey.service.BeyService;

/**
 * Clase encargada de mapear los endpoints REST de la app
 * 
 * @author Jhon Lara
 *
 */
@RestController
@RequestMapping(value = "/bey")
public class BeyController {

	@Autowired
	private BeyService beyService;

	@PostMapping("/list")
	public ResponseEntity<String> createProducts(@RequestBody List<Product> products) {
		try {
			beyService.createProducts(products);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BeyException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@PostMapping()
	public ResponseEntity<String> createProduct(@RequestBody Product asset) {
		try {
			beyService.createProduct(asset);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BeyException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		try {
			beyService.updateProduct(product);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BeyException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@DeleteMapping("/{ID_PRODUCT}")
	public ResponseEntity<String> deleteProduct(@PathVariable(name = "ID_PRODUCT") Long idProduct) {
		try {
			beyService.deleteProduct(idProduct);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BeyException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping()
	public List<Product> getProducts() {
		return beyService.getProducts();
	}

	@GetMapping("/{ID_PRODUCT}")
	public Product getProduct(@PathVariable(name = "ID_PRODUCT") Long idProduct) {
		return beyService.getProductById(idProduct);
	}

}
