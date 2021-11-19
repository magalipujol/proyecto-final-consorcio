package com.proyectofinal.consorcio.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
	@Autowired
	private BCryptPasswordEncoder encoder;

    public Usuario getUserByLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return usuarioRepository.findByMail(auth.getName());
	}
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMail(mail);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getMail().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER"));

        UserDetails userDetails = new User(usuario.getMail(), usuario.getPassword(), authorities);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuario", usuario);            
        return userDetails;
    }
    
    @Transactional
	public Usuario crearUsuario(String mail) throws Exception{
		try {
			validar(mail);

			Usuario usuario = new Usuario();			
			usuario.setMail(mail);
			usuario.setPassword(encoder.encode(mail));

			usuarioRepository.save(usuario);
			return usuario;
		} catch (Exception e) {
			throw new Exception ("Error al crearUsuario");
		}	
	}
    
    public Usuario cambiarContrasenia (String contraseniaNueva1, String contraseniaNueva2) throws Exception {
    	try {
    		Usuario usuario = getUserByLogin();
    		
			if (contraseniaNueva1.equals(contraseniaNueva2)) {
				usuario.setPassword(encoder.encode(contraseniaNueva1));
				
				usuarioRepository.save(usuario);
			}
			return usuario;
		} catch (Exception e) {
			throw new Exception ("Error al cambiar contraseña");
		}
    	
    }
	
	public void validar (String mail) throws Exception {
		if (mail==null || mail.isEmpty() || mail.contains("  ")) {
			throw new Exception ("Debe ingresar un mail válido");
		} 
		
		//VER MANEJO DE ERRRORES:
		if (usuarioRepository.findByMail(mail) != null) {
			throw new Exception ("El mail ya existe");
		}				
	}
}
