package com.proyectofinal.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.repositories.EdificioRepository;

@Service
public class EdificioService {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EdificioRepository edificioRepository;

	@Transactional
	public Edificio crear(String direccion) throws Exception {
		try {
			validar(direccion);
			
			Edificio edificio = new Edificio();
			edificio.setDireccion(direccion);
			edificio.setAlta(true);	
			edificio.setAdministrador(usuarioService.getUserByLogin());			

			edificioRepository.save(edificio);
			
			return edificio;
		} catch (Exception e) {
			throw new Exception("Error al crearEdificio");
		
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
	
	@Transactional(readOnly = true)
	public Edificio buscarPorId (Long id) throws Exception {
		try {
			Edificio edificio = edificioRepository.findById(id).get();
			return edificio;
		} catch (Exception e) {
			throw new Exception ("Error al buscar edificio por id");
		}
	}

	public void validar(String direccion) throws Exception {
		try {
			if (direccion == null || direccion.isEmpty()) {
				throw new Exception("La direcci�n no puede estar vac�a");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

}
