package com.gabrielsmm.gmcontrol.repositories;

import com.gabrielsmm.gmcontrol.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    Usuario findByNomeUsuario(String nomeUsuario);

    Usuario findByEmail(String email);

}
