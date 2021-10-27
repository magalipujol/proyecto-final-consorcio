package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, String>{
	
	

}
