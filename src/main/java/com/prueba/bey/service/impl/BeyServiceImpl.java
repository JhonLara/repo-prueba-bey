package com.prueba.bey.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.bey.entity.BeyException;
import com.prueba.bey.entity.Product;
import com.prueba.bey.repository.ProductRepository;
import com.prueba.bey.service.BeyService;

/**
 * Clase encargada de mapear y dar comportamiento a las capacidades de los
 * servicios
 * 
 * @author Jhon Lara
 *
 */
@Service
public class BeyServiceImpl implements BeyService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Constantes con las cadenas de mensaje para mostrar al usuario
	 */
	public static final String PRODUCT_NOT_FOUND = "El producto con este id no existe";

	public static final String PRODUCT_FOUND = "El producto con este id ya existe";

	public static final String PRODUCT_NOT_ID = "Debe ingresar el id del producto";

	public static final String PRODUCT_NEW_ID = "Debe ingresar el id definido";

	public static final String PRODUCT_DATE_AFTER = "La fecha de compra debe ser menor a la fecha de hoy";

	public static final String PRODUCT_VALUE_ERROR = "El valor debe de ser mayor a cero";

	/**
	 * Constante con el valor del porcentaje de depreciación
	 */
	private final double CONST_PERCENT = 0.04D;

	@Override
	public void createProducts(List<Product> products) {

		products.forEach(product -> {
			/**
			 * Se valida que no tenga id
			 */
			if (product.getIdProduct() == null) {
				productRepository.save(product);
			} else {
				throw new BeyException(PRODUCT_NEW_ID);
			}
		});
	}

	@Override
	public void createProduct(Product product) {
		if (product.getIdProduct() == null) {
			/*
			 * Se valida que la fecha sea menor o igual a la de hoy
			 */
			if (product.getBuyDate().isEqual(LocalDate.now()) || product.getBuyDate().isBefore(LocalDate.now())) {
				/*
				 * Se valida que se haya ingresado un valor de compra
				 */
				if (product.getBuyValue() != null && product.getBuyValue() > 0) {
					Period periodo = Period.between(product.getBuyDate(), LocalDate.now());
					product.setDepreciationValue((long) ((CONST_PERCENT * periodo.getYears()) * product.getBuyValue()));
					productRepository.save(product);
				} else {
					throw new BeyException(PRODUCT_VALUE_ERROR);
				}
			} else {
				throw new BeyException(PRODUCT_DATE_AFTER);
			}
		} else {
			throw new BeyException(PRODUCT_NEW_ID);
		}

	}

	@Override
	public void deleteProduct(Long idProduct) {
		try {
			Optional<Product> optProduct = productRepository.findById(idProduct);
			if (optProduct.isPresent()) {
				productRepository.delete(optProduct.get());
			}
		} catch (NoSuchElementException nse) {
			throw new BeyException(PRODUCT_NOT_FOUND);
		}

	}

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long idProduct) {
		try {
			Optional<Product> optProduct = productRepository.findById(idProduct);
			return optProduct.get();
		} catch (NoSuchElementException nse) {
			throw new BeyException(PRODUCT_NOT_FOUND);
		}
	}

	@Override
	public void updateProduct(Product product) {
		try {
			if (product.getIdProduct() != null) {
				Optional<Product> optProduct = productRepository.findById(product.getIdProduct());
				productRepository.save(optProduct.get());
			} else {
				throw new BeyException(PRODUCT_NOT_ID);
			}
		} catch (NoSuchElementException nse) {
			throw new BeyException(PRODUCT_NOT_FOUND);
		}
	}

}
