package com.proyectofinal.consorcio.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.proyectofinal.consorcio.enums.TipoGasto;

@Entity
public class Egreso {

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String detalle;	
	private Double monto;
	private Boolean alta;
	
	private TipoGasto tipoGasto;
	
	@ManyToOne
	private Liquidacion liquidacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;


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

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	
	


}
