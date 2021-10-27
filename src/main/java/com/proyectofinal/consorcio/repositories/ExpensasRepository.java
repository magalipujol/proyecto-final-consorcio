package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Expensas;

@Repository
public interface ExpensasRepository extends JpaRepository<Expensas, String>{

}
