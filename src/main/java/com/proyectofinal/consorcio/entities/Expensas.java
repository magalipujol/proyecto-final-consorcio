package com.proyectofinal.consorcio.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.proyectofinal.consorcio.enums.Meses;
import com.proyectofinal.consorcio.enums.Movimientos;

@Entity
public class Expensas {

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String detalle;	
	private Double monto;
	private Boolean alta;
	
	@Enumerated(EnumType.STRING)
	private Movimientos movimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Meses mes;

	//GETTERS Y SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Movimientos getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimientos movimiento) {
		this.movimiento = movimiento;
	}

	public Meses getMes() {
		return mes;
	}

	public void setMes(Meses mes) {
		this.mes = mes;
	}


}
