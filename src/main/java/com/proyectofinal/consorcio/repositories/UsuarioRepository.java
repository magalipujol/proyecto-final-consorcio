package com.proyectofinal.consorcio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofinal.consorcio.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByMail(String mail);
	/*@Query("SELECT a from Administrador a WHERE a.email LIKE :email")
	public Administrador buscarPorEmail(@Param("email") String email);*/
}
