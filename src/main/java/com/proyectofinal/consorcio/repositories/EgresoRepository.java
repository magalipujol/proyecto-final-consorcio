package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Egreso;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, String> {

}
