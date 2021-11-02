package com.proyectofinal.consorcio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.proyectofinal.consorcio.enums.Meses;

@Entity
public class Liquidacion {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Meses mes;	
	@ManyToOne
	private Edificio edificio;
	private Double total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Meses getMes() {
		return mes;
	}

	public void setMes(Meses mes) {
		this.mes = mes;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
}
