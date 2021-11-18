package com.proyectofinal.consorcio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Liquidacion;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, String>{	
	@Query("SELECT l FROM Liquidacion l WHERE l.alta = true AND l.edificio.id = :id ORDER BY l.anio DESC")
	public List<Liquidacion> listarLiquidacionesAdmin(@Param("id") Long id);
	
	@Query("SELECT l FROM Liquidacion l WHERE l.alta = true AND l.edificio.id = :id_edificio")
	public List<Liquidacion> listarLiquidacionesEdificio(@Param("id_edificio") Long id);
	
	//@Query("SELECT l FROM Liquidacion l WHERE l.publicar = true AND l.alta = true ORDER BY l.anio DESC")
	
	//falta que publicar sea true
	@Query("SELECT l FROM Liquidacion l JOIN Edificio e ON e.id = l.edificio.id JOIN Departamento d ON e.id = d.edificio.id JOIN Usuario u ON u.id = d.usuario.id WHERE u.id = :id AND l.publicar = true")
	public List<Liquidacion> listarLiquidacionesUsuario(@Param("id") String id);
	
	
	@Query("SELECT SUM(e.monto) FROM Egreso e WHERE e.alta = true AND e.liquidacion.id = :id")
	public Double totalEgresos(@Param("id") String id);
	
	@Query("SELECT SUM(e.monto) FROM Egreso e WHERE e.alta = true AND e.liquidacion.id = :id AND e.tipoGasto LIKE 'ORDINARIO'")
	public Double totalOrdinarios(@Param("id") String id);
	
	@Query("SELECT SUM(e.monto) FROM Egreso e WHERE e.alta = true AND e.liquidacion.id = :id AND e.tipoGasto LIKE 'EXTRAORDINARIO'")
	public Double totalExtraordinarios(@Param("id") String id);	
}
