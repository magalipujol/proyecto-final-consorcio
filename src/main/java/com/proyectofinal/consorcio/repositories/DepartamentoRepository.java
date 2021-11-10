package com.proyectofinal.consorcio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
	
	
	@Query("SELECT d FROM Departamento d WHERE d.edificio.id = :id ORDER BY d.piso, d.dpto")
	public List<Departamento> buscarDptos (@Param("id") Long id);
	
	@Query("SELECT d FROM Departamento d WHERE d.usuario.id LIKE :id")
	public Departamento buscarPorUsuario (@Param("id") String id);
	
}
