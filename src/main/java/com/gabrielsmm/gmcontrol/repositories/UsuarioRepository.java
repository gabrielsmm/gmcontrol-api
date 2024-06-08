package com.gabrielsmm.gmcontrol.repositories;

import com.gabrielsmm.gmcontrol.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuario(String usuario);

    Usuario findByEmail(String email);

}
