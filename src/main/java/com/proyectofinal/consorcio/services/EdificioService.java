package com.proyectofinal.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.EdificioRepository;

@Service
public class EdificioService {

	@Autowired
	private EdificioRepository edificioRepository;

	@Transactional
	public void crear(String direccion, Usuario usuario) {
		try {
			validar(direccion);
			Edificio edificio = new Edificio();
			edificio.setDireccion(direccion);
			edificio.setAlta(true);

			// Ver usuario
			edificio.setAdministrador(usuario);

			edificioRepository.save(edificio);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en crearEdificio");
		}
	}

	@Transactional
	public void modificar(Long id, String direccion) {
		try {
			validar(direccion);
			Edificio edificio = edificioRepository.findById(id).get();
			edificio.setDireccion(direccion);
			edificioRepository.save(edificio);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en modificarEdificio");
		}
	}

	@Transactional
	public void darDeBaja(Long id) {
		try {
			Edificio edificio = edificioRepository.findById(id).get();

			edificio.setAlta(false);

			edificioRepository.save(edificio);

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en dar de baja edificio");
		}
	}

	@Transactional(readOnly = true)
	public List<Edificio> listarActivos() throws Exception {
		try {
			return edificioRepository.buscarActivos();
		} catch (Exception e) {
			throw new Exception("Error al listar edificios");
		}
	}

	public void validar(String direccion) throws Exception {
		try {
			if (direccion == null || direccion.isEmpty()) {
				throw new Exception("La dirección no puede estar vacía");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

}
