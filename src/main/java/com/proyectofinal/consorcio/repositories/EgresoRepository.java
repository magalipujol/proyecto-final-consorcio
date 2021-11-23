package com.proyectofinal.consorcio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Egreso;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, String> {	
	@Query("SELECT e FROM Egreso e WHERE e.alta = true AND e.liquidacion.id = :id ORDER BY e.tipoGasto")
	public List<Egreso> buscarEgresos (@Param("id") String id);
	
	@Query("SELECT e FROM Egreso e WHERE e.alta = true AND e.tipoGasto LIKE 'ORDINARIO' AND e.liquidacion.id = :id")
	public List<Egreso> buscarOrdinarios (@Param("id") String id);
	
	@Query("SELECT e FROM Egreso e WHERE e.alta= true AND e.tipoGasto LIKE 'EXTRAORDINARIO' AND e.liquidacion.id = :id ")
	public List<Egreso> buscarExtraordinarios (@Param("id") String id);
}
