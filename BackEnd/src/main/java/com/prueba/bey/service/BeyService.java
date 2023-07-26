package com.prueba.bey.service;

import java.util.List;

import com.prueba.bey.entity.Product;

/**
 * Clase encargada de declarar la estructura de las capacidades que se expondran
 * en la implementación del servicio
 * 
 * @author Jhon Lara
 *
 */
public interface BeyService {

	public void createProducts(List<Product> products);

	public void createProduct(Product product);

	public void deleteProduct(Long idProduct);

	public List<Product> getProducts();

	public Product getProductById(Long idProduct);

	public void updateProduct(Product product);

}
