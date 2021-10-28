package com.proyectofinal.consorcio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Administrador;
import com.proyectofinal.consorcio.repositories.AdministradorRepository;



@Service
public class AdministradorService {
		
	@Autowired
	private AdministradorRepository adminRepository;
	
	@Transactional
	public void crearUsuario (String nombre, String mail, String password) {
		Administrador admin = new Administrador();
		admin.setMail(mail);
		admin.setNombre(nombre);
		admin.setPassword(password);
		
		adminRepository.save(admin);
		
		
	}
/*
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Administrador user = adminRepository.buscarPorEmail(email);
		
		if (user != null) {
			List<GrantedAuthority> permissions = new ArrayList<>();
			GrantedAuthority p = new SimpleGrantedAuthority("ROLE_ADMIN");
			permissions.add(p);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuario", user);
			return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(),
					permissions);
		}
		return null;

	}*/
	
}
