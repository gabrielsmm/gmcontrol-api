package com.gabrielsmm.gmcontrol.services;

import com.gabrielsmm.gmcontrol.entities.Usuario;
import com.gabrielsmm.gmcontrol.repositories.UsuarioRepository;
import com.gabrielsmm.gmcontrol.security.UserSS;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomeUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSS(usuario.getId(), usuario.getNomeUsuario(), usuario.getSenha(), usuario.getPerfisEnum());
    }

}
