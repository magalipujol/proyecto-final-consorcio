package com.proyectofinal.consorcio.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Expensas {

	
	@Id
	private String id;
	private Double total;
	private String detalle;
	private Double monto;
	private final Integer ingreso = 1;
	private final Integer egreso = 2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//GETTERS Y SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public Integer getIngreso() {
		return ingreso;
	}

	public Integer getEgreso() {
		return egreso;
	}
	

	
	

}
