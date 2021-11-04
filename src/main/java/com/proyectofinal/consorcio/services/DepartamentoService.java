package com.proyectofinal.consorcio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Transactional
	public void crear(Integer piso, String dpto, Double porcentaje, Edificio edificio, Usuario usuario) {
		try {
			validar(piso, dpto, porcentaje);
			
			Departamento departamento = new Departamento();
			departamento.setPiso(piso);
			departamento.setDpto(dpto);
			departamento.setPorcentajeParticipacion(porcentaje);
			//Ver edificio
			departamento.setEdificio(edificio);
			//Ver usuario
			departamento.setUsuario(usuario);


			departamentoRepository.save(departamento);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en crear departamento");
		}
	}

	@Transactional
	public void modificar(String id, Integer piso, String dpto, Double porcentaje) {
		try {
			validar(piso, dpto, porcentaje);
			Departamento departamento = departamentoRepository.findById(id).get();
			departamento.setPiso(piso);
			departamento.setDpto(dpto);
			departamento.setPorcentajeParticipacion(porcentaje);
			
			departamentoRepository.save(departamento);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en modificar departamento");
		}
	}

	@Transactional
	public void eliminar(String id) {
		try {
			Departamento departamento = departamentoRepository.findById(id).get();

			departamentoRepository.delete(departamento);

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error al eliminar departamento");
		}
	}

	/*
	@Transactional(readOnly = true)
	public List<Departamento> listarActivos() throws Exception {
		try {
			return departamentoRepository.buscarDepartamentos();
		} catch (Exception e) {
			throw new Exception("Error al listar departamentos");
		}
	}*/

	public void validar(Integer piso, String dpto, Double porcentaje) throws Exception {
		try {
			//Validar piso
			if (piso == null || dpto.equals(null) || porcentaje == null) {
				throw new Exception("Este dato no puede ser nulo");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	
	

}
