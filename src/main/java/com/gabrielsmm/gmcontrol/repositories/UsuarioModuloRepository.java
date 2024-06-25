package com.gabrielsmm.gmcontrol.repositories;

import com.gabrielsmm.gmcontrol.entities.UsuarioModulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioModuloRepository extends JpaRepository<UsuarioModulo, Long> {

    List<UsuarioModulo> findByUsuarioId(Long usuarioId);

    Optional<UsuarioModulo> findByUsuarioIdAndModulo(Long usuarioId, Integer modulo);

}
