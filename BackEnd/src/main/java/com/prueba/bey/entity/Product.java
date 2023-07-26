package com.prueba.bey.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase encargada de mapear la entidad producto con sus diferentes atributos
 * 
 * @author Jhon Lara
 *
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "ID_PRODUCT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long idProduct;

	@Column(name = "SERIAL_NUMBER")
	private Long serialNumber;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BUY_DATE")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate buyDate;

	@Column(name = "BUY_VALUE")
	private Long buyValue;

	@Column(name = "DEPRECATION_VALUE")
	private Long depreciationValue;

}
