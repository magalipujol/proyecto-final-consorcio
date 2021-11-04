package com.proyectofinal.consorcio.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUserByLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return usuarioRepository.findByMail(auth.getName());
	}

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMail(mail);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getMail().equals("pepe@gg.com") ? "ADMIN" : "USER"));

        UserDetails userDetails = new User(usuario.getMail(), usuario.getPassword(), authorities);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuario", usuario);            
        return userDetails;
    }
}
