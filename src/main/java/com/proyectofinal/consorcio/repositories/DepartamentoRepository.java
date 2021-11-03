package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
	
	//Ver listar departamentos de un edificio en particular
	/*
	@Query("SELECT d FROM Departamento d WHERE d.edificio.id = :id")
	public List<Departamento> buscarActivos ();*/
}
