package com.proyectofinal.consorcio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long>{	
	@Query("SELECT e FROM Edificio e WHERE e.alta = true")
	public List<Edificio> buscarActivos ();	
}
