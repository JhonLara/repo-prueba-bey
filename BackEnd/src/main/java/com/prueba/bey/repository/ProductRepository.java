package com.prueba.bey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.bey.entity.Product;

/**
 * Clase que obtiene todas las capacidades JPA para la entidad producto
 * 
 * @author Jhon Lara
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
