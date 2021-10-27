package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {

}
