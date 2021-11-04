package com.proyectofinal.Consorcio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ConsorcioApplicationTests {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void contextLoads() {
	}

	@Test
	public void crearUsuarioTest() {
		Usuario usuario = new Usuario();
		usuario.setMail("pepe@gg.com");
		usuario.setPassword(encoder.encode("pwpwpw"));

		Usuario usuarioCreado = usuarioRepository.save(usuario);		

		assertTrue(usuarioCreado.getPassword().equalsIgnoreCase(usuario.getPassword()));
	}
}
