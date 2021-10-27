package com.proyectofinal.consorcio.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Edificio {

	@Id
	private String id;
	private String direccion;	
	
	private Expensas expensas;

	@OneToMany
	private Departamento departamento;
	
	//GETTERS Y SETTERS

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Expensas getExpensas() {
		return expensas;
	}

	public void setExpensas(Expensas expensas) {
		this.expensas = expensas;
	}
	
	

}
