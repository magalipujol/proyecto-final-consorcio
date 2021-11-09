package com.proyectofinal.consorcio.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	private Meses mes;	
	private Integer anio;
	@ManyToOne
	private Edificio edificio;
	private Double total;
	private Double totalOrdinarios;
	private Double totalExtraordinarios;
	private Boolean alta;
	private Boolean publicar;

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
	

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	
	
	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}
	

	public Boolean getPublicar() {
		return publicar;
	}

	public void setPublicar(Boolean publicar) {
		this.publicar = publicar;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalOrdinarios() {
		return totalOrdinarios;
	}

	public void setTotalOrdinarios(Double totalOrdinarios) {
		this.totalOrdinarios = totalOrdinarios;
	}

	public Double getTotalExtraordinarios() {
		return totalExtraordinarios;
	}

	public void setTotalExtraordinarios(Double totalExtraordinarios) {
		this.totalExtraordinarios = totalExtraordinarios;
	}
	
	
}
